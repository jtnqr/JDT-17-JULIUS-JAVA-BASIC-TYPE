package com.indivaragroup.task.grading;

public class GradingIf {
    public final int GRADE_PASSING = 65, GRADE_APPRECIATION = 90, GRADE_REPEAT = 75;

    public int activityGrade = 100, examGrade = 85, presenceGrade = 90;

    public void runGrading() {
        int averageGrade = (activityGrade + examGrade + presenceGrade) / 3;
        System.out.println("Nilai rata-rata: " + averageGrade);

        if (averageGrade > GRADE_PASSING) {
            if (averageGrade >= GRADE_APPRECIATION) {
                System.out.println("Selamat anda mendapatkan nilai baik");
            }
            if (averageGrade < GRADE_APPRECIATION) {
                if (averageGrade < GRADE_REPEAT) {
                    System.out.println("Anda harus mengikuti remedial!");
                }
                if (averageGrade >= GRADE_REPEAT) {
                    System.out.println("Selamat anda lulus!");
                }
            }
        }
        if (averageGrade <= GRADE_PASSING) {
            System.out.println("Anda harus ulang lagi tahun depan!");
        }
    }
}
