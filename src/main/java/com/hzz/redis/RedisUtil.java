package com.hzz.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class RedisUtil {
    private JedisPool jedisPool;

    public RedisUtil(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public Jedis getResource() {
        return this.jedisPool.getResource();
    }

    public void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }

    }

    public void execute(Consumer<Jedis> fun) {
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            fun.accept(jedis);
        } finally {
            this.returnResource(jedis);
        }

    }

    public Object execute(Function<Jedis, Object> fun) {
        Jedis jedis = null;

        Object var3;
        try {
            jedis = this.getResource();
            var3 = fun.apply(jedis);
        } finally {
            this.returnResource(jedis);
        }

        return var3;
    }

    public void pipeline(Consumer<Pipeline> fun) {
        Jedis jedis = null;

        try {
            jedis = this.getResource();
            Pipeline pl = jedis.pipelined();
            fun.accept(pl);
            pl.sync();
        } finally {
            this.returnResource(jedis);
        }

    }

    public List<Object> pipelineReturnAll(Consumer<Pipeline> fun) {
        Jedis jedis = null;

        List var4;
        try {
            jedis = this.getResource();
            Pipeline pl = jedis.pipelined();
            fun.accept(pl);
            var4 = pl.syncAndReturnAll();
        } finally {
            this.returnResource(jedis);
        }

        return var4;
    }

    public List<String> scanKeys(String pattern, int perCount) {
        List<String> keylist = new ArrayList();
        this.execute((jedis) -> {
            ScanParams scanParams = new ScanParams();
            scanParams.match(pattern);
            scanParams.count(perCount);
            String scanRet = "0";

            ScanResult ret;
            do {
                ret = jedis.scan(scanRet, scanParams);
                scanRet = ret.getStringCursor();
                keylist.addAll(ret.getResult());
            } while(!ret.isCompleteIteration());

        });
        return keylist;
    }
}
