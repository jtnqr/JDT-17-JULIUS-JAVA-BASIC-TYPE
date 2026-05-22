package com.indivaragroup.operator;

public class BitwiseOperator {
    public void runDemonstration(){
        int a = 5;
        int b = 3;

        System.out.println(a & b);   // 1
        System.out.println(a | b);   // 7
        System.out.println(a ^ b);   // 6
        System.out.println(~a);      // -6
        System.out.println(a << 1);  // 10
        System.out.println(a >> 1);  // 2
    }
}