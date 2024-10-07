package com.workflow.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class WarriorUtil {

    public static Integer generate100() {
        return (int) (Math.random() * 100);
    }

    public static Boolean generateBoolean() {
        return new Random().nextBoolean();
    }
}
