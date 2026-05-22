package com.indivaragroup.task.todo.service;

import com.indivaragroup.task.todo.dto.MetricsDto;
import com.indivaragroup.task.todo.dto.TodoDto;
import com.indivaragroup.task.todo.entity.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TodoService {
    private final List<Todo> todos = new ArrayList<>();
    private long idCounter = 1;

    public TodoDto addTodo(TodoDto dto) {
        Todo todo = new Todo(
                idCounter++,
                dto.getProject(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getAssignee(),
                dto.getPriority(),
                "Open",
                LocalDate.parse(dto.getStartDate()), //
                LocalDate.parse(dto.getDueDate()),
                dto.getEstimatedHour(),
                0.0,
                dto.getWeight(),
                dto.getReviewer(),                  //
                dto.getCreatedBy()                   //
        );
        todos.add(todo);
        return mapToDto(todo);
    }

    public List<TodoDto> getAllTodos() {
        return todos.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public boolean updateStatus(Long id, String newStatus, double actualHourUpdate) {
        Optional<Todo> todoOpt = todos.stream().filter(t -> t.getId().equals(id)).findFirst();
        if (todoOpt.isPresent()) {
            Todo todo = todoOpt.get();

            // Business Rule 8: Reviewer must approve before status transitions to Done [cite: 59]
            if (newStatus.equalsIgnoreCase("Done") && !todo.getStatus().equalsIgnoreCase("Ready Review")) {
                System.out.println("Business Rule Violation: Task must be in 'Ready Review' status before moving to 'Done'.");
                return false;
            }

            todo.setStatus(newStatus);
            if (actualHourUpdate > 0) {
                todo.setActualHour(actualHourUpdate);
            }
            return true;
        }
        return false;
    }

    public MetricsDto calculateMetrics(String assigneeName) {
        // 4.3 Progress Project: Total Bobot Task Selesai / Total Bobot Semua Task x 100 [cite: 29]
        // Business Rule 3: Cancelled tasks are omitted from calculations [cite: 19, 54]
        double totalWeight = todos.stream()
                .filter(t -> !t.getStatus().equalsIgnoreCase("Cancelled"))
                .mapToDouble(Todo::getWeight)
                .sum();

        double earnedWeight = todos.stream()
                .filter(t -> !t.getStatus().equalsIgnoreCase("Cancelled"))
                .mapToDouble(t -> t.getWeight() * (getStatusProgress(t.getStatus()) / 100.0)) // [cite: 34, 35, 61]
                .sum();

        double projectProgress = totalWeight > 0 ? (earnedWeight / totalWeight) * 100 : 0;

        // 4.4 Workload Assignee: Total Estimated Hour for Active tasks (Open, In Progress, Ready Review) [cite: 36, 37, 38, 39, 40]
        double workload = todos.stream()
                .filter(t -> t.getAssignee().equalsIgnoreCase(assigneeName))
                .filter(t -> List.of("OPEN", "IN PROGRESS", "READY REVIEW").contains(t.getStatus().toUpperCase()))
                .mapToDouble(Todo::getEstimatedHour)
                .sum();

        // 4.5 Variance & 4.6 Productivity: Filtered to Done tasks [cite: 41, 48, 52]
        List<Todo> doneTasks = todos.stream()
                .filter(t -> t.getAssignee().equalsIgnoreCase(assigneeName))
                .filter(t -> t.getStatus().equalsIgnoreCase("Done"))
                .collect(Collectors.toList());

        double totalEstimated = doneTasks.stream().mapToDouble(Todo::getEstimatedHour).sum();
        double totalActual = doneTasks.stream().mapToDouble(Todo::getActualHour).sum();

        // Variance = Actual Hour - Estimated Hour [cite: 42, 43, 46, 47]
        double variance = totalActual - totalEstimated;

        // Productivity = Estimated Hour / Actual Hour x 100 [cite: 49, 50]
        double productivity = totalActual > 0 ? (totalEstimated / totalActual) * 100 : 0;

        return MetricsDto.builder()
                .totalProjectProgress(projectProgress)
                .assigneeWorkload(workload)
                .timeVariance(variance)
                .productivityPercentage(productivity)
                .build();
    }

    // 4.1 Progress Berdasarkan Status [cite: 13]
    private double getStatusProgress(String status) {
        switch (status.toUpperCase()) {
            case "OPEN": return 0.0;          // [cite: 14]
            case "IN PROGRESS": return 50.0;   // [cite: 15]
            case "READY REVIEW": return 80.0;  // [cite: 16]
            case "DONE": return 100.0;        // [cite: 17, 18, 56]
            default: return 0.0;              // Cancelled [cite: 19, 54]
        }
    }

    private TodoDto mapToDto(Todo todo) {
        double progress = getStatusProgress(todo.getStatus());

        // Business Rule 6: SLA Overdue check
        String slaStatus = "On Time";
        if (!todo.getStatus().equalsIgnoreCase("Done") && LocalDate.now().isAfter(todo.getDueDate())) {
            slaStatus = "Overdue"; //
        }

        return TodoDto.builder()
                .id(todo.getId())
                .project(todo.getProject())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .assignee(todo.getAssignee())
                .priority(todo.getPriority())
                .status(todo.getStatus())
                .startDate(todo.getStartDate().toString())
                .dueDate(todo.getDueDate().toString())
                .estimatedHour(todo.getEstimatedHour())
                .actualHour(todo.getActualHour())
                .weight(todo.getWeight())
                .reviewer(todo.getReviewer())
                .createdBy(todo.getCreatedBy())
                .progressPercentage(progress)
                .slaStatus(slaStatus)
                .build();
    }
}