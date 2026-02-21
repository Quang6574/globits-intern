package com.globits.demo.dao;

import com.globits.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

@Repository
public interface TaskDAO
        extends JpaRepository<Task, Integer>, JpaSpecificationExecutor<Task> {

}
