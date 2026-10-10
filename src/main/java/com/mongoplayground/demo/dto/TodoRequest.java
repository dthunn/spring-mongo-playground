package com.mongoplayground.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class TodoRequest {

    @NotBlank(message = "title must not be blank")
    private String title;

    private boolean completed;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
