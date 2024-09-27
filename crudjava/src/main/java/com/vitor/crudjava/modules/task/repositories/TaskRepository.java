package com.vitor.crudjava.modules.task.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitor.crudjava.modules.task.models.TaskEntity;


@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long>{
    List<TaskEntity> findAllBy(Pageable pageable);

    List<TaskEntity> findAllByIsDone(Boolean isDone, Pageable pageable);
} 