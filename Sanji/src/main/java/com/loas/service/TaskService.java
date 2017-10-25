package com.loas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.loas.model.Task;

public interface TaskService {
	
	void addTask(Task task);
	
	Iterable<Task> findAllTasks();
	
	List<Task> getAllTasks();
	
//	List<Task> search(String d);
	
//	Task findTaskById(int id);
	
	Task findOne(int id);
	
	void delete(int id);
	
	void save(Task task);
	
	Page<Task> findAllPagable(Pageable pageable);
	

}
