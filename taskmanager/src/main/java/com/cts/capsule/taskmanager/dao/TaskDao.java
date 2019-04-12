/**
 * 
 */
package com.cts.capsule.taskmanager.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cts.capsule.taskmanager.bean.ParentTask;
import com.cts.capsule.taskmanager.bean.Task;
import com.cts.capsule.taskmanager.repository.ParentTaskRepository;
import com.cts.capsule.taskmanager.repository.TaskRepository;

/**
 * @author 269012
 *
 */
@Repository("taskDao")
public class TaskDao {

	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private ParentTaskRepository parentTaskRepository;
	
	public ParentTask addTask(ParentTask parentTask) throws Exception {
		ParentTask newParentTask = parentTaskRepository.save(parentTask);
		return newParentTask;
	}
	
	public Task getTask(String taskId) throws Exception {
		Task task = taskRepository.findByParent(taskId);
		return task;
	}
	
	public List<ParentTask> fetchTasks() throws Exception {
		List<ParentTask> tasks = parentTaskRepository.findAll();
		return tasks;
	}
}
