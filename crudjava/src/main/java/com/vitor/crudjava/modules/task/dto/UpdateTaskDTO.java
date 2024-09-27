package com.vitor.crudjava.modules.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateTaskDTO {
    @NotBlank
    @NotNull(message = "O campo title é obrigatório")
    private String title;
    @NotNull(message = "O campo isDone é obrigatório")
    private Boolean isDone;

    // Getters e Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }
}
