package com.example.niche_test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.niche_test.dao.TaskDao;
import com.example.niche_test.dto.Task;
import com.example.niche_test.exception.NoTasksException;
import com.example.niche_test.exception.TaskNotFoundException;
import com.example.niche_test.util.ResponseStructure;

@Service
public class TaskService {

	@Autowired
	private TaskDao dao;

	public ResponseEntity<ResponseStructure<Task>> addTask(Task task) {
		Task dbTask = dao.addTask(task);
		ResponseStructure<Task> structure = new ResponseStructure<Task>();

		if (dbTask != null) {
			structure.setMessage("Task added successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(task);

			return new ResponseEntity<ResponseStructure<Task>>(structure, HttpStatus.NOT_ACCEPTABLE);
		} else {
			structure.setMessage("Task adding failed");
			structure.setStatus(HttpStatus.BAD_REQUEST.value());
			structure.setData(task);

			return new ResponseEntity<ResponseStructure<Task>>(structure, HttpStatus.NOT_ACCEPTABLE);
		}
	}

	public ResponseEntity<ResponseStructure<List<Task>>> viewAllTasks() throws NoTasksException{

		List<Task> tasks = dao.viewAllTasks();
		ResponseStructure<List<Task>> structure = new ResponseStructure<>();

		if (!tasks.isEmpty()) {
			structure.setMessage("Tasks fetched...");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(tasks);

			return new ResponseEntity<ResponseStructure<List<Task>>>(structure, HttpStatus.OK);
		}

		throw new NoTasksException("0 customer ! no customer exist");
	}

	public ResponseEntity<ResponseStructure<String>> deleteTaskById(int id) throws TaskNotFoundException {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		
		if(dao.deleteTaskById(id))
		{
			structure.setMessage("Customer deleted...");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData("Deleted");
			
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		
		throw new TaskNotFoundException("Task not found with given id");
	}

	public ResponseEntity<ResponseStructure<Task>> updateTaskDetails(int id, Task task) throws TaskNotFoundException {
	
		Task dbTask = dao.updateTaskDetails(id, task);
		
		ResponseStructure<Task> structure = new ResponseStructure<Task>();
		
		if(dbTask != null) {
			structure.setMessage("Task update successfully..!");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dbTask);
			return new ResponseEntity<ResponseStructure<Task>>(structure,HttpStatus.CREATED);
		}
		throw new TaskNotFoundException("Task not found with given id");
	}

	public ResponseEntity<ResponseStructure<List<Task>>> viewAllCompletedTasks(boolean status) {
		
		List<Task> tasks = dao.viewAllCompletedTasks(status);
		ResponseStructure<List<Task>> structure = new ResponseStructure<>();

		if (!tasks.isEmpty()) {
			structure.setMessage("Tasks fetched...");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(tasks);

			return new ResponseEntity<ResponseStructure<List<Task>>>(structure, HttpStatus.OK);
		}

		throw new NoTasksException("0 customer ! no customer exist");
	}
}
