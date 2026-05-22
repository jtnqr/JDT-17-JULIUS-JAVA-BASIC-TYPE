package com.indivaragroup.type.array;


import java.util.Arrays;

public class ArrayDataType {
    public void runDemonstration() {
        int[][] data = {
                {1,2,3},
                {4,5,6},
        };

        int nilai = data[1][2];

        int[][] matrix = new int[2][3];
        matrix[0][0] = 10;
        matrix[0][1] = 20;

        System.out.println("Nilai: " + nilai);
        System.out.println("Matrix: " + Arrays.deepToString(matrix));
    }
}
