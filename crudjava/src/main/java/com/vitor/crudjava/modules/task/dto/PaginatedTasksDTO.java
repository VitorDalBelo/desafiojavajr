package com.vitor.crudjava.modules.task.dto;

import java.util.List;

public class PaginatedTasksDTO {
    private List<GetTaskDTO> tasks;
    private boolean hasNextPage;
    
    public PaginatedTasksDTO(List<GetTaskDTO> tasks, boolean hasNextPage) {
        this.tasks = tasks;
        this.hasNextPage = hasNextPage;
    }

    public List<GetTaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<GetTaskDTO> tasks) {
        this.tasks = tasks;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    
}
