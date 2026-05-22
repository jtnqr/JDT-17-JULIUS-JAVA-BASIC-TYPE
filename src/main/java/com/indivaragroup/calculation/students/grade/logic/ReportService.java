package com.indivaragroup.calculation.students.grade.logic;

import com.indivaragroup.calculation.students.grade.dto.GradeReport;
import com.indivaragroup.calculation.students.grade.dto.StudentRecord;

public class ReportService {
    public static final int GRADE_A = 100;
    public static final int GRADE_B = 90;
    public static final int GRADE_C = 70;

    public GradeReport generateReport(StudentRecord[] inputs) {
        for (StudentRecord record : inputs) {
            int grade = record.getGrade();
            String name = record.getName();

            if (grade < 0 || grade > 100) {
                System.out.println(name + ": Input tidak valid!");
                continue;
            }

            System.out.print(name + " -> ");
            if (grade == GRADE_A) {
                System.out.println("Jago banget!");
            } else if (grade >= GRADE_B) {
                System.out.println("Jago aja!");
            } else if (grade >= GRADE_C) {
                System.out.println("Biasa aja!");
            } else {
                System.out.println("Belajar lagi!");
            }
        }

        return new GradeReport(inputs);
    }
}