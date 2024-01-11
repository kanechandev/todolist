package com.kanechan.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanechan.todolist.model.Task;
import com.kanechan.todolist.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public Task save(Task task) {
		return taskRepository.save(task);
	}

}
