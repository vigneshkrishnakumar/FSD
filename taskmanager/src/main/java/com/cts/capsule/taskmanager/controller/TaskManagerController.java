/**
 * 
 */
package com.cts.capsule.taskmanager.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.capsule.taskmanager.bean.ParentTask;
import com.cts.capsule.taskmanager.bean.Task;
import com.cts.capsule.taskmanager.service.TaskService;

/**
 * Controller class for Task Manager Application
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping({"/task"})
public class TaskManagerController {

	@Autowired
	private TaskService taskService;
	
	Logger logger = LoggerFactory.getLogger(TaskManagerController.class);
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;
	
	@PostMapping
	public ParentTask saveTask(@RequestBody ParentTask task) {
		ParentTask newParentTask = null;
		try {
			newParentTask = taskService.addTask(task);
			logger.info(messageSource.getMessage("task.added", new Object[0], null));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return newParentTask;
	}
	
	@PutMapping("/updateTask/{id}")
	public ParentTask updateTask(@PathVariable String id, @RequestBody ParentTask parentTask) {
		ParentTask dbParentTask = null;
		try {
			dbParentTask = taskService.getTask(id).getParentTask();
			logger.info("Existing task retrieved");
			if(dbParentTask != null) {
				dbParentTask.setParentTask(parentTask.getParentTask());
				dbParentTask.getNewTask().setTaskName(parentTask.getNewTask().getTaskName());
				dbParentTask.getNewTask().setPriority(parentTask.getNewTask().getPriority());
				dbParentTask.getNewTask().setStartDate(parentTask.getNewTask().getStartDate());
				dbParentTask.getNewTask().setEndDate(parentTask.getNewTask().getEndDate());
				dbParentTask = taskService.addTask(dbParentTask);
				logger.info(messageSource.getMessage("task.updated", new Object[0], Locale.US));
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return dbParentTask;
	}
	
	@GetMapping("/fetchTasks")
	public List<ParentTask> getTaskList() {
		List<ParentTask> taskList = null;
		try {
			taskList = taskService.fetchTasks();
			logger.info(messageSource.getMessage("tasks.fetched", new Object[0], Locale.US));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return taskList;
	}
	
	@GetMapping("/getTask/{taskId}")
	public ParentTask getTask(@PathVariable String taskId) {
		Task task = null;
		try {
			task = taskService.getTask(taskId);
			logger.info(messageSource.getMessage("task.fetched", new Object[0], Locale.US));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return task.getParentTask();
	}
}
