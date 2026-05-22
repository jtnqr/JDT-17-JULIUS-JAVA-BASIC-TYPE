package com.indivaragroup.calculation.registration.identity.logic;

import com.indivaragroup.calculation.registration.identity.dto.CalculationRegistrationIdentity;
import java.math.BigDecimal;

public class RegistrationService {
    private static final BigDecimal KTP_THRESHOLD = new BigDecimal("10000");
    private static final BigDecimal KTP_FEE = new BigDecimal("5000");

    public CalculationRegistrationIdentity processRegistration(BigDecimal initialMoney) {
        System.out.println("Uang Awal: " + initialMoney);
        BigDecimal finalMoney = initialMoney;

        if (initialMoney.compareTo(KTP_THRESHOLD) == 0) {
            System.out.println("Harus buat KTP!");
            finalMoney = initialMoney.subtract(KTP_FEE);
        } else {
            System.out.println("Tidak harus buat KTP!");
        }

        System.out.println("Uang Akhir: " + finalMoney);
        return new CalculationRegistrationIdentity(finalMoney);
    }
}