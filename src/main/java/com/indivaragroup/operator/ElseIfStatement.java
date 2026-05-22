package com.indivaragroup.operator;

public class ElseIfStatement {
    public void runDemonstration() {
        int nilai = 80;

        if (nilai >= 90) {
            System.out.println("A");
        } else if (nilai >= 75) {
            System.out.println("B");
        } else if (nilai >= 60) {
            System.out.println("C");
        } else {
            System.out.println("D");
        }
    }
}
