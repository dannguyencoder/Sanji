package com.loas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loas.model.Task;
import com.loas.repository.TaskRepository;
import com.loas.service.TaskService;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public void addTask(Task task) {
		taskRepository.save(task);		
	}

	@Override
	public Iterable<Task> findAllTasks() {
		// TODO Auto-generated method stub
		return taskRepository.findAll();
	}

	@Override
	public List<Task> getAllTasks() {
		// TODO Auto-generated method stub
		return taskRepository.findAll();
	}

//	@Override
//	public List<Task> search(String d) {
//		// TODO Auto-generated method stub
//		return taskRepository.findByTaskName(d);
//	}
//
//	@Override
//	public Task findTaskById(int id) {
//		// TODO Auto-generated method stub
//		return taskRepository.findByTaskId(id);
//	}

	@Override
	public Task findOne(int id) {
		// TODO Auto-generated method stub
		return taskRepository.findOne(id);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		taskRepository.delete(id);
		
	}

	@Transactional
	@Override
	public void save(Task task) {
		// TODO Auto-generated method stub
		taskRepository.saveAndFlush(task);
		
	}

	@Transactional
	@Override
	public Page<Task> findAllPagable(Pageable pageable) {
		return taskRepository.findAll(pageable);
	}
	

}
