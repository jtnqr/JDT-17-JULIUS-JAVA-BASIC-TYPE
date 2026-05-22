package com.indivaragroup.operator;

public class UnaryStatement {
    public void runDemonstration () {
        int a = 5;

        System.out.println(++a);      // pre-increment : 6
        System.out.println(a++);      // post-increment : 6
        System.out.println(a);        // after post-increment : 7

        System.out.println(--a);      // pre-decrement : 6
        System.out.println(a--);      // post-decrement : 6
        System.out.println(a);        // after post-decrement : 5

        int b = -a;                   // negation : -5

        boolean isTrue = true;
        System.out.println(!isTrue);  // false
    }
}