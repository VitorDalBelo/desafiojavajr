package com.vitor.crudjava.modules.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.crudjava.exceptions.ExceptionResponse;

import com.vitor.crudjava.modules.task.dto.CreateTaskDTO;
import com.vitor.crudjava.modules.task.dto.GetTaskDTO;
import com.vitor.crudjava.modules.task.dto.PaginatedTasksDTO;
import com.vitor.crudjava.modules.task.dto.UpdateTaskDTO;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/tasks")
@Tag(name="tasks", description = "Endpoints de tarefas")
public class TasksController {
    @Autowired
    private TasksService tasksService;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida",
                content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
        @ApiResponse(responseCode = "404", description = "Nenhuma tarefa encontrada",
                content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping()
    public PaginatedTasksDTO getTasks(@RequestParam(defaultValue = "10") @Min(1) int limit,
                                            @RequestParam(defaultValue = "0") @Min(0) int offset,
                                            @RequestParam(required = false) Boolean isDone) {

        return tasksService.getAllTasks(limit, offset , isDone);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida",
                content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
   @PostMapping()
    public GetTaskDTO createCliente(@Valid @RequestBody CreateTaskDTO payload){

        return tasksService.createCliente(payload);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida",
                content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })

    @PutMapping("/{id}")
    public GetTaskDTO updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskDTO updateTaskDTO) {
        return tasksService.updateTask(id, updateTaskDTO);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida",
                content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
        @ApiResponse(responseCode = "404", description = "Nenhuma tarefa encontrada",
                content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        tasksService.deleteTask(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }

    
}

