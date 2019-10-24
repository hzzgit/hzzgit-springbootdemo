package com.hzz.lamdba;

import com.hzz.springbootdao.util.ConverterUtils;
import net.fxft.cloud.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class mans {



    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(122, "小猫"));
        students.add(new Student(22, "小够"));
        students.add(new Student(55, "小猪"));
        students.add(new Student(55, "小猪1"));
        students.add(new Student(55, "小猪2"));
        students.add(new Student(55, "小猪2"));
        students.add(new Student(55, "小猪4"));
        students.add(new Student(55, "小猪5"));
        students.add(new Student(122, "小猪777"));
        students.add(new Student(122, "小猪513"));
        students.sort(Comparator.comparing(Student::getSex).reversed());

        System.out.println(students);
//        Map<Integer, List<Student>> studentsMap = new HashMap<>();
//        for (Student student : students) {
//            List<Student> studentList = studentsMap.getOrDefault(student.getSex(), new ArrayList<>());
//            studentList.add(student);
//            studentsMap.put(student.getSex(), studentList);
//        }
//
//        Map<String, List<Student>> studentsMap1 = students.stream().collect(Collectors.groupingBy(Student::getName));
//        System.out.println(studentsMap1);
//
//
//        new Thread(() -> System.out.println(1)).start();
//        new Thread(() -> System.out.println(1)).start();
//        List<String> evens = Arrays.asList("21", "11", "33", "344");
//
//        List<Integer> integers = Arrays.asList(1, 2, 33, 44, 22, 11, 22, 31313, 44);
//
//
//        List<Integer> l = map(Arrays.asList("lambda", "in", "action"), (String s) -> s.length());
//        System.out.println(l);
//
//
//        students.sort(Comparator.comparing(Student::getSex).reversed().thenComparing(Student::getName));
//
//        System.out.println(students);


//        String name = test1("2121", (String s) -> s.substring(2));
//        System.out.println(name);
//
//        test2(name, arr -> {
//            arr = arr.substring(1);
//        });
//
//       List<String > names= test3(students,arr->{
//           return arr.getName().substring(1, 2);
//        });
//
//        for (String s : names) {
//            System.out.println(s);
//        }
//
//        Jedis jedis = new Jedis("localhost");
//        jedis.set("name1", "sdas");
//        //存储数据到列表中
//        jedis.lpush("site-list", "Runoob");
//        jedis.lpush("site-list", "Google");
//        jedis.lpush("site-list", "Taobao");
//        // 获取存储的数据并输出
//        List<String> list = jedis.lrange("site-list", 0 ,2);
//        for(int i=0; i<list.size(); i++) {
//            System.out.println("列表项为: "+list.get(i));
//        }
//
//
//
//        // 获取数据并输出
//        Set<String> keys = jedis.keys("*");
//        for (String key : keys) {
//            jedis.del(key);
//        }


        JedisPoolConfig config = new JedisPoolConfig();

        JedisPool pool = new JedisPool("localhost");
//        JedisPool pool = new JedisPool(config,"r-bp143d2ed3392654.redis.rds.aliyuncs.com",6379,222321,"1jfrH8XLvx4wnbGNpY5Rr7SM",0);
        Jedis jedis = pool.getResource();
        Set<String> strings = jedis.keys("*");

        //1.存储值
        jedis.set("name", "张三");
//2.取值
        String name = jedis.get("name");
        System.out.println(name);
//3.key命令：查看有效期，-1表示持久化
        Long t = jedis.ttl("name");
        Long time = jedis.ttl("name");
        System.out.println(t);
//4.key命令,对已经存在的key设置过期时间
        jedis.expire("name", 5);
        String name2 = jedis.get("name");
        System.out.println(name2);
        System.out.println("有效期为:" + jedis.ttl("name") + "秒");

        jedis.close();

        RedisUtil redisUtil=new RedisUtil(pool);

        long s3=System.currentTimeMillis();   //获取开始时间
        redisUtil.pipeline(pipeline -> {
            for (String string : strings) {
                pipeline.del(string);
            }
        });
        long e3=System.currentTimeMillis(); //获取结束时间

        System.out.println("删除用时"+(e3-s3)+"ms");

        List<String> list1= (List<String>) redisUtil.execute(jedis1 -> {
            jedis1.setex("name", 5, "xiaom");

            System.out.println(jedis1.ttl("name"));
            System.out.println(jedis1.get("name"));




            List<String> na1 = new ArrayList<>();
            String name1 = "";
            for (int i = 0; i <20000 ; i++) {
                na1.add("a" + i);
                na1.add("ab" + i);
            }
            long s2=System.currentTimeMillis();   //获取开始时间
            for (String s1 : na1) {
                jedis1.set(s1+"b", s1 + "ab1");
            }
            long e2=System.currentTimeMillis(); //获取结束时间
            System.out.println(e2-s2+"ms");

            long s=System.currentTimeMillis();   //获取开始时间
            reidshzz.pipine(pipeline -> {
                for (String s1 : na1) {
                    pipeline.set(s1 + "sca", s1 + "sas");
                }
            });

            long e=System.currentTimeMillis(); //获取结束时间
            System.out.println(e-s+"ms");
                
            List<String> list = jedis1.mget("a1","a2","a3","a4","a5","a6");
            return list;
        });
        for (String s : list1) {
            System.out.println(s);
        }

    }




    public static List<String> test3(List<Student> students, Function<Student, String> fun) {
        List<String> list = new ArrayList<>();
        for (Student student : students) {
            String name = fun.apply(student);
            list.add(name);
        }
        return list;
    }

    public static <T> void test2(T aa, Consumer<T> f) {
        f.accept(aa);
    }


    public static <T, R> R test1(T aa, Function<T, R> f) {
        return f.apply(aa);
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<R>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }
}
