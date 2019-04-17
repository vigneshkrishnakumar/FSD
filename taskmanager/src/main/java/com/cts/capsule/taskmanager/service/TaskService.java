/**
 * 
 */
package com.cts.capsule.taskmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.capsule.taskmanager.bean.ParentTask;
import com.cts.capsule.taskmanager.bean.Task;
import com.cts.capsule.taskmanager.dao.TaskDao;

/**
 * Service class for Tasks
 *
 */
@Service("taskService")
public class TaskService {

	@Autowired
	private TaskDao taskDao;
	
	public ParentTask addTask(ParentTask parentTask) throws Exception {
		ParentTask newParentTask = new ParentTask();
		newParentTask = taskDao.addTask(parentTask);
		return newParentTask;
	}
	
	public Task getTask(String taskId) throws Exception {
		Task task = new Task();
		task = taskDao.getTask(taskId);
		return task;
	}
	
	public List<ParentTask> fetchTasks() throws Exception {
		List<ParentTask> taskList = new ArrayList<>();
		taskList = taskDao.fetchTasks();
		return taskList;
	}
}
