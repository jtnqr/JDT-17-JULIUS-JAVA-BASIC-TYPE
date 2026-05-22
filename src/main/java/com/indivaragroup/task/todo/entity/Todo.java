package com.indivaragroup.task.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private Long id;
    private String project;
    private String title;
    private String description;
    private String assignee;
    private String priority;
    private String status; // Open, In Progress, Ready Review, Done, Cancelled
    private LocalDate startDate;
    private LocalDate dueDate;
    private double estimatedHour;
    private double actualHour;
    private double weight;
    private String reviewer;
    private String createdBy;
}