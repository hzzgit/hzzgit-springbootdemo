package com.hzz.absolt;

public abstract class abclase {

    public abstract boolean acceptData(String key);

    public void init(String name){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    acceptData(name);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
