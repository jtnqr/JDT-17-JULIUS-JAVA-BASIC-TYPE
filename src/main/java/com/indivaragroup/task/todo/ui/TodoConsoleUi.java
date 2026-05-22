package com.indivaragroup.task.todo.ui;

import com.indivaragroup.task.todo.dto.MetricsDto;
import com.indivaragroup.task.todo.dto.TodoDto;
import com.indivaragroup.task.todo.service.TodoService;

import java.util.List;
import java.util.Scanner;

public class TodoConsoleUi {
    private final TodoService todoService = new TodoService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": addTodo(); break;
                case "2": listTodos(); break;
                case "3": updateTodoStatus(); break;
                case "4": viewMetrics(); break;
                case "5":
                    System.out.println("Exiting application.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== MVP TODO SYSTEM ===");
        System.out.println("1. Add New Task");
        System.out.println("2. List All Tasks");
        System.out.println("3. Update Task Status & Actual Hours");
        System.out.println("4. View Metrics (Progress, Workload, Productivity)");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    private void addTodo() {
        try {
            System.out.print("Project Name: "); String project = scanner.nextLine().trim();
            System.out.print("Task Title: "); String title = scanner.nextLine().trim();
            System.out.print("Description: "); String desc = scanner.nextLine().trim();
            System.out.print("Created By: "); String creator = scanner.nextLine().trim(); //
            System.out.print("Assignee (PIC): "); String assignee = scanner.nextLine().trim(); //
            System.out.print("Reviewer (PIC Review): "); String reviewer = scanner.nextLine().trim(); //
            System.out.print("Priority (Low/Medium/High/Critical): "); String priority = scanner.nextLine().trim(); //
            System.out.print("Start Date (YYYY-MM-DD): "); String startDate = scanner.nextLine().trim(); //
            System.out.print("Due Date (YYYY-MM-DD): "); String dueDate = scanner.nextLine().trim(); //
            System.out.print("Estimated Hours: "); double est = Double.parseDouble(scanner.nextLine().trim()); //
            System.out.print("Task Weight: "); double weight = Double.parseDouble(scanner.nextLine().trim()); //

            TodoDto inputDto = TodoDto.builder()
                    .project(project).title(title).description(desc).createdBy(creator)
                    .assignee(assignee).reviewer(reviewer).priority(priority)
                    .startDate(startDate).dueDate(dueDate).estimatedHour(est).weight(weight)
                    .build();

            TodoDto result = todoService.addTodo(inputDto);
            System.out.printf("Successfully created task ID: %d (Status: %s)%n", result.getId(), result.getStatus());
        } catch (Exception e) {
            System.out.println("Error creating task. Ensure numbers and date formats (YYYY-MM-DD) are correct.");
        }
    }

    private void listTodos() {
        List<TodoDto> todos = todoService.getAllTodos();
        if (todos.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-3s | %-10s | %-15s | %-10s | %-10s | %-11s | %-5s%% | %-8s | %-6s%n",
                "ID", "Project", "Title", "Assignee", "Reviewer", "Status", "Prog", "SLA", "Weight");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        for (TodoDto t : todos) {
            System.out.printf("%-3d | %-10s | %-15s | %-10s | %-10s | %-11s | %-5.0f%% | %-8s | %-6.1f%n",
                    t.getId(), t.getProject(), t.getTitle(), t.getAssignee(), t.getReviewer(), t.getStatus(),
                    t.getProgressPercentage(), t.getSlaStatus(), t.getWeight());
        }
    }

    private void updateTodoStatus() {
        listTodos();
        try {
            System.out.print("\nEnter Task ID to update: ");
            Long id = Long.parseLong(scanner.nextLine().trim());
            System.out.print("New Status (Open/In Progress/Ready Review/Done/Cancelled): ");
            String status = scanner.nextLine().trim();
            System.out.print("Actual Hours Spent (Total): ");
            double hours = Double.parseDouble(scanner.nextLine().trim());

            if (todoService.updateStatus(id, status, hours)) {
                System.out.println("Task updated successfully.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input data format.");
        }
    }

    private void viewMetrics() {
        System.out.print("Enter Assignee Name to view metrics: ");
        String name = scanner.nextLine().trim();
        MetricsDto metrics = todoService.calculateMetrics(name);

        System.out.println("\n=== METRICS SUMMARY (MVP RECOMMENDATION) ===");
        System.out.printf("Total Project Weighted Progress : %.2f%%%n", metrics.getTotalProjectProgress()); // [cite: 29, 61]
        System.out.printf("Active Workload for %s     : %.1f Hours%n", name, metrics.getAssigneeWorkload()); // [cite: 39, 61]
        System.out.printf("Time Variance (Actual - Est)    : %+.1f Hours%n", metrics.getTimeVariance()); // [cite: 42, 61]
        System.out.printf("Productivity Rate (Est/Actual)  : %.2f%%%n", metrics.getProductivityPercentage()); //
    }
}