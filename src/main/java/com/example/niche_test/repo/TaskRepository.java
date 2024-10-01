package com.example.niche_test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.niche_test.dto.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{

	@Query("select t FROM Task t")
	List<Task> viewAllTasks();

	List<Task> findByStatus(boolean status);
}
