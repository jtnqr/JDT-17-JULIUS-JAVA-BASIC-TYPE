package com.indivaragroup.calculation.datatype.dto;

import lombok.Value;

@Value
public class ConversionResultDto {
    int widenedInt;
    double widenedDouble;
    int narrowedInt;
    byte narrowedByte;
}