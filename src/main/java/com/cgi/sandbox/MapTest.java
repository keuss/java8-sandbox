package com.cgi.sandbox;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    private static void printerMap(Object key, Object val) {
        System.out.println("Autre traitement ...");
        System.out.println(key + "=>" +val);
    }

    public static void main(String[] args) {
        Map<Integer, String> stringMap = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            stringMap.putIfAbsent(i, "val " + i);
        }

        // Without refactoring (see printerMap) :
        //(key, val) -> System.out.println(key + "=>" +val);
        /*stringMap.forEach((key, val) -> {
                System.out.println("Autre traitement ...");
                System.out.println(key + "=>" + val);
            }
        );*/
        stringMap.forEach(MapTest::printerMap);


    }
}
