package com.indivaragroup.calculation.datatype.logic;

import com.indivaragroup.calculation.datatype.dto.ConversionResultDto;

public class DataTypeConversionService {

    public ConversionResultDto executeConversion(byte initialByte, double initialDouble) {
        int widenedInt = initialByte;
        double widenedDouble = widenedInt;

        int narrowedInt = (int) initialDouble;
        byte narrowedByte = (byte) narrowedInt;

        return new ConversionResultDto(widenedInt, widenedDouble, narrowedInt, narrowedByte);
    }
}