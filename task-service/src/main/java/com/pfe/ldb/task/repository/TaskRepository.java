package com.pfe.ldb.task.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.pfe.ldb.entity.TaskEntity;
import com.pfe.ldb.entity.UserEntity;

public interface TaskRepository extends  CrudRepository<TaskEntity,Integer> {

	List<TaskEntity> findByTaskGroupId(Integer taskGroupId);

}
