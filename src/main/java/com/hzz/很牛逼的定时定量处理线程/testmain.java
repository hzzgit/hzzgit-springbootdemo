package com.hzz.很牛逼的定时定量处理线程;

import com.hzz.lamdba.Student;
import net.fxft.cloud.metrics.Tps;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class testmain {

    private AbstractBatchExecThreadPoolExecutor<Integer, Student> execPool;

    private Tps consumerTps;
    private Tps producterTps;
    @PostConstruct
    private void init(){
        consumerTps = new Tps();
        producterTps = new Tps();
        execPool = new AbstractBatchExecThreadPoolExecutor<Integer, Student>("gpstopika",
                6, 500, 3, 1000) {
            @Override
            public Integer getQueueSplitKey(Student req) {
                return 0;
            }
            //这边就是到达时间和数量的时候会执行一次
            @Override
            public void batchRun(Integer splitKey, List<Student> reqlist) {
                System.out.println("数量"+reqlist.size());
            }
        };

        while (true){
            for (int i = 0; i <2000 ; i++) {
                execPool.submit(new Student(1,"1"));
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }






}
