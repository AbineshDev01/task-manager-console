package com.taskapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.taskapp.model.Task;

public class TaskService {
    private List<Task> tasks;
    private int nextId;

    public TaskService() {
        tasks = new ArrayList<>();
        nextId = 1;
    }

    public void addTask(String title, String description, String status, String priority) {
        Task task = new Task(nextId++, title, description, priority, status);
        tasks.add(task);
        System.out.println("Task added");
    }

    public Task getTask(Integer taskId) {
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) return task;
        }
        return null;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void editTask(Integer taskId, String title, String description, String status, String priority) {
        Task task = getTask(taskId);
        if (task != null) {
            if (!title.isEmpty()) task.setTitle(title);
            if (!description.isEmpty()) task.setDescription(description);
            if (!status.isEmpty()) task.setStatus(status);
            if (!priority.isEmpty()) task.setPriority(priority);
            System.out.println("Task edited");
        }
    }

    public void deleteTask(Integer taskId) {
        Task task = getTask(taskId);
        if (task != null) {
            tasks.remove(task);
            System.out.println("Task deleted");
        }
    }

    public List<Task> getTasksByPriority(String priority) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    public String selectPriority(Scanner scanner, String currentPriority) {
        while (true) {
            try {
                System.out.println("1) High");
                System.out.println("2) Medium");
                System.out.println("3) Low");
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    return currentPriority;
                }
                int choice = Integer.parseInt(input);
                if(choice > 3) {
                    System.out.println("\n--------------------------------------------------");
                    System.out.println("Invalid input. Please enter a number between 1 - 3");
                    System.out.println("--------------------------------------------------");
                }
                switch (choice) {
                    case 1: return "High";
                    case 2: return "Medium";
                    case 3: return "Low";
                }
            } catch (NumberFormatException e) {
                System.out.println("\n--------------------------------------------------");
                System.out.println("Invalid input. Please enter a number between 1 - 3");
                System.out.println("--------------------------------------------------");
            }
        }
    }

    public String selectStatus(Scanner scanner, String currentStatus) {
        while (true) {
            try {
                System.out.println("1) Pending");
                System.out.println("2) In Progress");
                System.out.println("3) Completed");
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    return currentStatus;
                }
                int choice = Integer.parseInt(input);
                if(choice > 3) {
                    System.out.println("\n--------------------------------------------------");
                    System.out.println("Invalid input. Please enter a number between 1 - 3");
                    System.out.println("--------------------------------------------------");
                }
                switch (choice) {
                    case 1: return "Pending";
                    case 2: return "In Progress";
                    case 3: return "Completed";
                }
            } catch (NumberFormatException e) {
                System.out.println("\n--------------------------------------------------");
                System.out.println("Invalid input. Please enter a number between 1 - 3");
                System.out.println("--------------------------------------------------");
            }
        }
    }

    public boolean isIdPresent(Integer taskId) {
        Task task = getTask(taskId);
        return task != null;
    }
}
