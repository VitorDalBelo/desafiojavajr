package com.vitor.crudjava.modules.task;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.stream.Collectors;


import com.vitor.crudjava.exceptions.httpExceptions.ConflictException;
import com.vitor.crudjava.exceptions.httpExceptions.NotFoundException;
import com.vitor.crudjava.modules.task.dto.CreateTaskDTO;
import com.vitor.crudjava.modules.task.dto.GetTaskDTO;
import com.vitor.crudjava.modules.task.dto.PaginatedTasksDTO;
import com.vitor.crudjava.modules.task.dto.UpdateTaskDTO;
import com.vitor.crudjava.modules.task.repositories.TaskRepository;

import jakarta.validation.Valid;

import com.vitor.crudjava.modules.task.models.TaskEntity;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;


@Service
public class TasksService {
    @Autowired
    TaskRepository taskRepository;


    public PaginatedTasksDTO getAllTasks(int limit, int offset,Boolean isDone) {
        Pageable pageable = PageRequest.of(offset, limit);
        List<TaskEntity> tasks;

        if (isDone != null) {
            tasks = taskRepository.findAllByIsDone(isDone, pageable);
        } else {
            tasks = taskRepository.findAllBy(pageable);
        }
        if(tasks.size()==0) throw new NotFoundException("Nenhuma tarefa encontrada");
        List<GetTaskDTO> taskDTOs = tasks.stream().map(GetTaskDTO::new).collect(Collectors.toList());

        boolean hasNextPage = taskRepository.count() > (offset + 1) * limit;

        return new PaginatedTasksDTO(taskDTOs, hasNextPage);
    }


    public GetTaskDTO createCliente(@Valid CreateTaskDTO payload) {
        TaskEntity taskEntity = taskRepository.save(CreateTaskDTO.toEntity((payload)));

        return new GetTaskDTO(taskEntity);
    }


    public GetTaskDTO updateTask(Long taskId, @Valid UpdateTaskDTO updateTaskDTO) {
        // Buscar a tarefa pelo ID
        TaskEntity existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Tarefa não encontrada"));
    
        // Atualizar os campos
        if (updateTaskDTO.getTitle() != null) {
            existingTask.setTitle(updateTaskDTO.getTitle());
        }
        if (updateTaskDTO.getIsDone() != null) {
            existingTask.setIsDone(updateTaskDTO.getIsDone());
        }
    
        // Salvar a tarefa atualizada
        TaskEntity updatedTask = taskRepository.save(existingTask);
        return new GetTaskDTO(updatedTask);
    }


    public void deleteTask(Long taskId) {
        // Verificar se a tarefa existe
        if (!taskRepository.existsById(taskId)) {
            throw new NotFoundException("Tarefa não encontrada");
        }
        
        // Deletar a tarefa
        taskRepository.deleteById(taskId);
    }
    
}
