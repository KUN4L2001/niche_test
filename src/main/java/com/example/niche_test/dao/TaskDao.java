package com.example.niche_test.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.niche_test.dto.Task;
import com.example.niche_test.repo.TaskRepository;

@Repository
public class TaskDao {

	@Autowired
	private TaskRepository repository;

	public Task addTask(Task task) {
		
		return repository.save(task);
	}

	public List<Task> viewAllTasks() {
		
		return repository.viewAllTasks();
	}

	public boolean deleteTaskById(int id) {
	
		Optional<Task> task = repository.findById(id);
		
		if(task.isPresent())
		{
			repository.deleteById(id);
			return true;
		}
		
		return false;
	}

	public Task updateTaskDetails(int id, Task task) {
	
		Optional<Task> taskDB = repository.findById(id);
		
		if(taskDB.isPresent())
		{
		       Task existingTask = taskDB.get(); 
		        existingTask.setTask(task.getTask()); 
		        existingTask.setStatus(task.isStatus()); 
		        return repository.save(existingTask); 
		}
		return null;
	}

	public List<Task> viewAllCompletedTasks(boolean status) {
		
		return repository.findByStatus(status);
	}
}
