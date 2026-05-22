package com.indivaragroup.operator;

public class AugmentedAssignment {
    public void runDemonstration () {
        int a = 10;
        a += 5;   // a = a + 5  -> 15
        a -= 3;   // a = a - 3  -> 12
        a *= 2;   // a = a * 2  -> 24
        a /= 4;   // a = a / 4  -> 6

        int b = 20;
        b %= 6;   // b = b % 6  -> 2

        System.out.println(a);
        System.out.println(b);
    }
}