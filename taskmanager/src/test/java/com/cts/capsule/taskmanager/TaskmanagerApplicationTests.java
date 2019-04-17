package com.cts.capsule.taskmanager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.capsule.taskmanager.bean.ParentTask;
import com.cts.capsule.taskmanager.bean.Task;
import com.cts.capsule.taskmanager.service.TaskService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskmanagerApplicationTests {
	
	@Test
	public void contextLoads() {
	}
	
	@TestConfiguration
    static class TaskManagerImplTestContextConfiguration {
  
        @Bean
        public TaskService taskServiceTest() {
            return new TaskService();
        }
    }

	@Mock
    private TaskService taskServiceTest;
	
	private List<ParentTask> taskList;
	private Task task;

	@Before
	public void setUpSearch() throws Exception {
		String taskId = "2";
		task = new Task();
		task.setTaskId(2);
		task.setTaskName("Test Task 1");
		task.setPriority(12);
		task.setStartDate(new Date());
		task.setEndDate(new Date());

		Mockito.when(taskServiceTest.getTask(taskId))
				.thenReturn(task);
	}
	
	@Test
	public void whenValidId_thenTaskShouldBeFound() throws Exception {
		String taskId = "2";
		Task found = taskServiceTest.getTask(taskId);

		assertThat(found.getTaskName()).isEqualTo(task.getTaskName());
	}

	@Test
	public void whenTaskIsAdded() throws Exception {
		ParentTask parentTask = new ParentTask();
		Task task = new Task();
		task.setTaskId(1);
		task.setTaskName("Test Task 1");
		task.setPriority(12);
		task.setStartDate(new Date());
		task.setEndDate(new Date());
		parentTask.setParentId(1);
		parentTask.setParentTask("Test Parent 1");
		parentTask.setNewTask(task);
		Mockito.doReturn(parentTask).when(taskServiceTest).addTask(parentTask);
	}

	@Before
	public void setUpFetch() throws Exception {
		taskList = new ArrayList<ParentTask>();
		ParentTask parentTask = new ParentTask();
		Task task = new Task();
		task.setTaskId(1);
		task.setTaskName("Test Task 1");
		task.setPriority(12);
		task.setStartDate(new Date());
		task.setEndDate(new Date());
		parentTask.setParentId(1);
		parentTask.setParentTask("Test Parent 1");
		parentTask.setNewTask(task);
		taskList.add(parentTask);

		Mockito.when(taskServiceTest.fetchTasks())
				.thenReturn(taskList);
	}
	
	@Test
	public void fetchAllTasks() throws Exception {
		
		ParentTask parentTask = new ParentTask();
		Task task = new Task();
		task.setTaskId(1);
		task.setTaskName("Test Task 1");
		task.setPriority(12);
		task.setStartDate(new Date());
		task.setEndDate(new Date());
		parentTask.setParentId(1);
		parentTask.setParentTask("Test Parent 1");
		parentTask.setNewTask(task);
		taskList.add(parentTask);
		List<ParentTask> dbTasks = taskServiceTest.fetchTasks();

		assertThat(dbTasks).isEqualTo(taskList);
	}
}
