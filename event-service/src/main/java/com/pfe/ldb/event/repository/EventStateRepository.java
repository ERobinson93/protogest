package com.pfe.ldb.event.repository;

import org.springframework.data.repository.CrudRepository;
import com.pfe.ldb.entity.EventStateEntity;

public interface EventStateRepository extends  CrudRepository<EventStateEntity,Integer> {

	EventStateEntity findByName(String name);
}
