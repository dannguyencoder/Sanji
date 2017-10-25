package com.loas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.loas.model.Task;

@Repository("taskRepository")
public interface TaskRepository extends JpaRepository<Task, Integer>, PagingAndSortingRepository<Task, Integer> {
	
//	List<Task> findByTaskName(String q);
	
//	Task findByTaskId(int id);
	

}
