/**
 * 
 */
package com.cts.capsule.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.capsule.taskmanager.bean.ParentTask;
import com.cts.capsule.taskmanager.bean.Task;
import com.cts.capsule.taskmanager.dao.TaskDao;

/**
 * @author 269012
 *
 */
@Service("taskService")
public class TaskService {

	@Autowired
	private TaskDao taskDao;
	
	public ParentTask addTask(ParentTask parentTask) throws Exception {
		ParentTask newParentTask = taskDao.addTask(parentTask);
		return newParentTask;
	}
	
	public Task getTask(String taskId) throws Exception {
		Task task = taskDao.getTask(taskId);
		return task;
	}
	
	public List<ParentTask> fetchTasks() throws Exception {
		List<ParentTask> taskList = taskDao.fetchTasks();
		return taskList;
	}
}
