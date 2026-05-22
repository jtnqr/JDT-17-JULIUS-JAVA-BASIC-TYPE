package com.indivaragroup.expression;

public class ExpressionStatement {
    public void runDemonstration () {
        int a = 10;
        int b = 5;

        int hasil1 = a + b;                  // 15
        boolean hasil2 = a > b;              // true
        String hasil3 = "Halo " + "Dunia";   // "Halo Dunia"
        int hasil4 = (a * 2) - b;            // 15
        double hasil5 = Math.sqrt(16);       // 4.0

        // Contoh expression yang menghasilkan single value
        int total = (3 + 7) * 2;
        boolean cek = (10 % 2) == 0;
        String nama = "Java".toUpperCase();

        System.out.println("Hasil 1: " + hasil1);
        System.out.println("Hasil 2: " + hasil2);
        System.out.println("Hasil 3: " + hasil3);
        System.out.println("Hasil 4: " + hasil4);
        System.out.println("Hasil 5: " + hasil5);
        System.out.println("Hasil 6: " + total);
        System.out.println("Hasil 7: " + cek);
        System.out.println("Hasil 8: " + nama);
    }
}
