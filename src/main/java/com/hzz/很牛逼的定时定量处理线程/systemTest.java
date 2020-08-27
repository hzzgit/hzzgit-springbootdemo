package com.hzz.很牛逼的定时定量处理线程;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/27 16:15
 */
public class systemTest {

    public static void main(String[] args) {
        SecurityManager s = System.getSecurityManager();
        ThreadGroup threadGroup1 = s.getThreadGroup();
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(1);
    }
}
