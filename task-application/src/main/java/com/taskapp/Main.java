package com.taskapp;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.taskapp.model.Task;
import com.taskapp.service.TaskService;

public class Main {
    public static void main(String[] args) {
        TaskService taskService = new TaskService();
        Scanner scanner = new Scanner(System.in);
        Integer choice = null;

        do {
            System.out.println("\n1) Add Task");
            System.out.println("2) Get Task");
            System.out.println("3) Edit Task");
            System.out.println("4) Delete Task");
            System.out.println("5) View All Tasks");
            System.out.println("6) Get All Tasks By Priority");
            System.out.println("7) Exit\n");
            System.out.println("Enter Your Choice (1-7):");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\n-----------------------------------------------------");
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
                System.out.println("-----------------------------------------------------");
                scanner.nextLine();
                continue;
            }

            switch(choice) {
                case 1:
                    System.out.println("Enter the Title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter the Description:");
                    String description = scanner.nextLine();
                    System.out.println("Select Priority:");
                    String priority = taskService.selectPriority(scanner, "");
                    System.out.println("Select Status:");
                    String status = taskService.selectStatus(scanner, "");
                    taskService.addTask(title, description, status, priority);
                    break;
                case 2:
                    try {
                        System.out.println("Enter the Task Id:");
                        Integer taskIdForTask = scanner.nextInt();
                        if (!taskService.isIdPresent(taskIdForTask)) {
                            System.out.println("\n-------------------------");
                            System.out.println("Task not found with Id: " + taskIdForTask);
                            System.out.println("-------------------------");
                        } else {
                            Task task = taskService.getTask(taskIdForTask);
                            System.out.println("\n" + "-".repeat(task.toString().length()));
                            System.out.println(task.toString());
                            System.out.println("-".repeat(task.toString().length()));
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("\n----------------------------------------------");
                        System.out.println("Invalid Task Id. Please enter a valid integer.");
                        System.out.println("----------------------------------------------");
                        scanner.nextLine();
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Enter the Task Id:");
                        int taskIdForEdit = scanner.nextInt();
                        scanner.nextLine();
                        if (!taskService.isIdPresent(taskIdForEdit)) {
                            System.out.println("\n-------------------------");
                            System.out.println("Task not found with Id: " + taskIdForEdit);
                            System.out.println("-------------------------");
                        } else {
                            Task existingTask = taskService.getTask(taskIdForEdit);
                            System.out.println("Enter new Title (Leave blank to keep existing):");
                            String newTitle = scanner.nextLine();
                            System.out.println("Enter new Description (Leave blank to keep existing):");
                            String newDescription = scanner.nextLine();
                            System.out.println("Select new Priority (Leave blank to keep existing):");
                            String newPriority = taskService.selectPriority(scanner, existingTask.getPriority());
                            System.out.println("Select new Status (Leave blank to keep existing):");
                            String newStatus = taskService.selectStatus(scanner, existingTask.getStatus());
                            taskService.editTask(taskIdForEdit, newTitle, newDescription, newStatus, newPriority);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("\n----------------------------------------------");
                        System.out.println("Invalid Task Id. Please enter a valid integer.");
                        System.out.println("----------------------------------------------");
                        scanner.nextLine();
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Enter the Task Id:");
                        int taskIdForDelete = scanner.nextInt();
                        if (!taskService.isIdPresent(taskIdForDelete)) {
                            System.out.println("\n-------------------------");
                            System.out.println("Task not found with Id: " + taskIdForDelete);
                            System.out.println("-------------------------");
                        } else {
                            taskService.deleteTask(taskIdForDelete);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("\n----------------------------------------------");
                        System.out.println("Invalid Task Id. Please enter a valid integer.");
                        System.out.println("----------------------------------------------");
                        scanner.nextLine();
                    }
                    break;
                case 5:
                    List<Task> allTasks = taskService.getAllTasks();
                    if (allTasks.isEmpty()) {
                        System.out.println("\n--------------");
                        System.out.println("Tasks is empty");
                        System.out.println("--------------");
                        System.out.println("");
                    } else {
                        System.out.println("\n" + "-".repeat(getMaxStringTask(allTasks).length()));
                        System.out.println("\t\t\t\tTasks");
                        System.out.println("-".repeat(getMaxStringTask(allTasks).length()));
                        for (Task task : allTasks) {
                            System.out.println(task.toString());
                        }
                        System.out.println("-".repeat(getMaxStringTask(allTasks).length()));
                    }
                    break;
                case 6:
                    System.out.println("\n---------------");
                    System.out.println("Select priority");
                    System.out.println("---------------");
                    String prioritySelect = taskService.selectPriority(scanner, "");
                    List<Task> tasksWithPriority = taskService.getTasksByPriority(prioritySelect);
                    if (!tasksWithPriority.isEmpty()) {
                        System.out.println("\n" + "-".repeat(getMaxStringTask(tasksWithPriority).length()));
                        System.out.println("\tTasks with " + prioritySelect);
                        System.out.println("-".repeat(getMaxStringTask(tasksWithPriority).length()));
                        for (Task printPriorityTask : tasksWithPriority) {
                            System.out.println(printPriorityTask.toString());
                        }
                        System.out.println("-".repeat(getMaxStringTask(tasksWithPriority).length()));
                    } else {
                        System.out.println("\n--------------");
                        System.out.println("Tasks is empty");
                        System.out.println("--------------");
                    }
                    break;
                case 7:
                    System.out.println("\n----------");
                    System.out.println("Exiting...");
                    System.out.println("----------");
                    break;
                default:
                    System.out.println("\n------------------");
                    System.out.println("Invalid Choice....");
                    System.out.println("------------------\n");
                    break;
            }

        } while (choice == null || choice != 7);
        scanner.close();
    }

    private static String getMaxStringTask(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "";
        }
        Task maxTask = tasks.get(0);
        for (Task task : tasks) {
            if (task.getTitle().length() > maxTask.getTitle().length()) {
                maxTask = task;
            }
        }
        return maxTask.toString();
    }
}
