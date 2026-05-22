package com.indivaragroup.operator;

public class BooleanOperator {
    public void runDemonstration () {
        boolean a = true;
        boolean b = false;

        System.out.println(a && b);     // false
        System.out.println(a && a);     // true
        System.out.println(a || b);     // true
        System.out.println(b || b);     // false
        System.out.println(!b);         // true
        System.out.println(!true);      // false
    }
}
