package com.qt.neo4j.utils;

import java.util.Random;

public class RandomUtil {

    /**
     * 随机生成1-20的随机整型数字
     */
    public static int getRandomColor(){
        Random rand = new Random();
        int i = rand.nextInt(19)+1;
        return i;
    }
}
