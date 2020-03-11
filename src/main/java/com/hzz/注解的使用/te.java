package com.hzz.注解的使用;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author hzz
 * @version 1.0
 * @date 2020/3/11 15:09
 */
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Data
@TraceLog()
public class te {
    private int sex;

    public static void main(String[] args) {
        te te = new te();

    }
}
