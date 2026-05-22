package com.indivaragroup.task.housing;

public class CreditSystem {
    //  umur < 21: reject
    public final int REQUIRED_AGE = 21;
    public final int MANDATORY_SPONSOR_UNTIL_AGE = 25;
    //  cicilanBulan > gaji * 40%: reject
    public final double REJECTED_CREDIT_PERCENTAGE = 0.4;
    //  masaCicilan > 20: reject
    public final int REJECTED_MAX_PERIOD = 20;
    public final int HIGH_THREAT_PERIOD = 15;
    public final int LOW_CREDIT_SALARY_THRESHOLD = 7_000_000;
    public final int HIGH_CREDIT_SALARY_THRESHOLD = 15_000_000;
    public final double HIGH_VALUE_CREDIT_PERCENTAGE = 0.3;

//    gaji > 15jt: cicilanBulan < 30% gaji: high value ? normal
//
//    gaji 7 - 14.999 && umur > 25: approve
//    gaji < 7 && masaCicilan >= 15: high threat credit
//
//    umur 21 - 25: butuh penjamin

    public int monthlyCredit;

    //  gaji, umur, hrgRumah, tenor, cicilanBulan
    public int age = 24;
    public int salary = 15_000_000;
    public long houseValue = 1_000_000_000L;
    public int creditPeriod = 16;

    public void runCalculation() {
//      cicilanBulan = (hrgRumah / masaCicilan) / 12
        monthlyCredit = (int) ((((double) houseValue / creditPeriod) / 12));

        System.out.println("Profil Kreditor:");
        System.out.println("Umur: " + age);
        System.out.println("Salary: " + salary);

        System.out.println("\nRencana Kredit: ");
        System.out.println("Harga Rumah: " + houseValue);
        System.out.println("Jangka Kredit: "+ creditPeriod);

        if (age < REQUIRED_AGE) {
            System.out.println("Ditolak: umur tidak mencukupi!");
        } else if (creditPeriod > REJECTED_MAX_PERIOD) {
            System.out.println("Ditolak: jangka cicilan terlalu lama!");
        } else if (monthlyCredit > (salary * REJECTED_CREDIT_PERCENTAGE)) {
//          cicilanBulan > gaji * 40%: reject
            System.out.println("Ditolak: cicilan melebihi 40% dari gaji!");
        } else if (salary < LOW_CREDIT_SALARY_THRESHOLD && creditPeriod >= HIGH_THREAT_PERIOD) {
//          gaji < 7 && masaCicilan >= 15: high threat credit
            System.out.println("Dipertimbangkan: resiko kredit tinggi!");
        } else if (salary > HIGH_CREDIT_SALARY_THRESHOLD && monthlyCredit < (salary * HIGH_VALUE_CREDIT_PERCENTAGE)) {
//          gaji > 15jt: cicilanBulan < 30% gaji: high value ? normal
            if (age <= MANDATORY_SPONSOR_UNTIL_AGE) {
                System.out.println("Disetujui: High Value Credit (Butuh Penjamin)!");
            } else {
                System.out.println("Disetujui: High Value Credit!");
            }
        } else if (age >= REQUIRED_AGE && age <= MANDATORY_SPONSOR_UNTIL_AGE) {
//          umur 21 - 25: butuh penjamin
            System.out.println("Disetujui: butuh penjamin!");
        } else {
//          gaji 7 - 14.999 && umur > 25: approve
            System.out.println("Disetujui!");
        }
    }
}