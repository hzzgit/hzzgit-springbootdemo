package com.hzz.注解的使用.ConditionalOnClass;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hzz
 * @version 1.0
 * @date 2020/4/16 10:19
 */
@Slf4j

public class Van {

    private Fighter fighter;

    public Van(Fighter fighter) {
        this.fighter = fighter;
    }

    public void fight(){
        System.out.println("van：boy next door,do you like 玩游戏");
        fighter.fight();
    }
}
