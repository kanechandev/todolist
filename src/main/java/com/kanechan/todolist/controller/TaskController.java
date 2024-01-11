package com.kanechan.todolist.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kanechan.todolist.model.Task;
import com.kanechan.todolist.repository.TaskRepository;
import com.kanechan.todolist.service.TaskService;
import com.kanechan.todolist.util.ExceptionToDoListJava;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskService taskService;
	
	@ResponseBody
	@PostMapping(value = "/salvarTask")
	public ResponseEntity<Task> salvarTask(@RequestBody @Valid Task task) throws ExceptionToDoListJava{
		
		return new ResponseEntity<Task>(taskService.save(task), HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping(value = "/deletarTaskPorId/{id}")
	public ResponseEntity<String> deletarTaskPorId(@PathVariable("id") Long id){
		taskRepository.deleteById(id);
		
		return new ResponseEntity<String>("Tarefa removida com sucesso!", HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarTaskPorId/{id}")
	public ResponseEntity<Task> buscarTaskPorId(@PathVariable("id") Long id) throws ExceptionToDoListJava {
		
		Task task = taskRepository.findById(id).orElse(null);
		
		if(task==null) {
			throw new ExceptionToDoListJava("Não encontrou tarefa com o código: "+id);
		}
		
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarTaskPorDesc/{desc}")
	public ResponseEntity<List<Task>> buscarTaskPorDesc(@PathVariable("desc") String desc) {
		
		List<Task> tasks = taskRepository.buscarAcessoDesc(desc.toUpperCase());
		
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
