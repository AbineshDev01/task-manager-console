package com.taskapp.model;

public class Task {

    private Integer id;
    private String title;
    private String description;
    private String priority;
    private String status;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Task() {

    }
    public Task(Integer id, String title, String description, String priority, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Description: " + description + ", Priority: " + priority
                + ", Status: " + status;
    }


}
