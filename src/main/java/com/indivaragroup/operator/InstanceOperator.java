package com.indivaragroup.operator;

public class InstanceOperator {
    public void runDemonstration() {
        Object value = "test";
        boolean isTrue = value instanceof String;

        System.out.println(value instanceof String);   // true
        System.out.println(value instanceof Integer);  // false
        System.out.println(!isTrue);                    // false
    }
}
