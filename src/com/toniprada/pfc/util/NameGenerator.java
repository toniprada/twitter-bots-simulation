package com.toniprada.pfc.util;

/**
 * Created by toni on 19/05/14.
 */
public class NameGenerator {

    public static String generate() {
        double dice = Math.random();
        if (dice < 0.5) {
            // case 1: real names
            return NamesList.getRandomName() + " " + NamesList.getRandomName();
        } else {
            // case 2: real name with numbers
            return NamesList.getRandomName() + ((int)(Math.random()*100));
        }
    }

}
