package com.example.niche_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.niche_test.dto.Task;
import com.example.niche_test.exception.TaskNotFoundException;
import com.example.niche_test.service.TaskService;
import com.example.niche_test.util.ResponseStructure;

@RestController
@RequestMapping("/tasksys")
public class Controller {

	@Autowired
	private TaskService service;
	
	@PostMapping("/add/task")
	public ResponseEntity<ResponseStructure<Task>> addTask(@RequestBody Task task) {
		return service.addTask(task);
	}
	
	@GetMapping("/view/all/tasks")
	public ResponseEntity<ResponseStructure<List<Task>>> viewALLTasks() {
		return service.viewAllTasks();
	}
	
	@DeleteMapping("/delete/by/id")
	public ResponseEntity<ResponseStructure<String>> deleteTaskById(@RequestParam int id) throws TaskNotFoundException {

		return service.deleteTaskById(id);
	}
	
	@PatchMapping("/update/task/details")
	public ResponseEntity<ResponseStructure<Task>> updateTaskDetails(@RequestParam int id, @RequestBody Task task) throws TaskNotFoundException {
		return service.updateTaskDetails(id, task);
	}
	
	@GetMapping("/view/all/completed/tasks")
	public ResponseEntity<ResponseStructure<List<Task>>> viewALLCompletedTasks(@RequestParam boolean status) {
		return service.viewAllCompletedTasks(status);
	}
}
