package com.indivaragroup.task.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Long id;
    private String project;
    private String title;
    private String description;
    private String assignee;
    private String priority;   // Low, Medium, High, Critical
    private String status;     // Open, In Progress, Ready Review, Done, Cancelled
    private String startDate;
    private String dueDate;    // YYYY-MM-DD
    private double estimatedHour;
    private double actualHour;
    private double weight;
    private double progressPercentage;
    private String reviewer;
    private String createdBy;
    private String slaStatus;  // On Time / Overdue
}