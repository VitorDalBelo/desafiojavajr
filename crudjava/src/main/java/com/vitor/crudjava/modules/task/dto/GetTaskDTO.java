package com.vitor.crudjava.modules.task.dto;

import com.vitor.crudjava.modules.task.models.TaskEntity;

public class GetTaskDTO {
    private Long id;
    private String title;
    private Boolean isDone;



    
    public GetTaskDTO(TaskEntity entity) {
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.isDone=entity.getIsDone();
    }




    public Long getId() {
        return id;
    }




    public void setId(Long id) {
        this.id = id;
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
