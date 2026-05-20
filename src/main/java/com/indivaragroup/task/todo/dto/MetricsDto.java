package com.indivaragroup.task.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricsDto {
    private double totalProjectProgress;
    private double assigneeWorkload;
    private double timeVariance;
    private double productivityPercentage;
}