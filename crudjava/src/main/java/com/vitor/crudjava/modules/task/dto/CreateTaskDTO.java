package com.vitor.crudjava.modules.task.dto;

import com.vitor.crudjava.modules.task.models.TaskEntity;

import jakarta.validation.constraints.NotNull;

public class CreateTaskDTO {
    @NotNull(message = "O campo title é obrigatório")
    private String title;
    @NotNull(message = "O campo isDone é obrigatório")
    private Boolean isDone;


    public static TaskEntity toEntity(CreateTaskDTO dto){
        return new TaskEntity(dto.getTitle(),dto.getIsDone());
    }


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
