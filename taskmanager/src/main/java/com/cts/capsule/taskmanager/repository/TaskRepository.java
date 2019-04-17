/**
 * 
 */
package com.cts.capsule.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.capsule.taskmanager.bean.Task;

/**
 * Repository for Tasks
 *
 */
@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Query("SELECT t FROM Task t JOIN FETCH t.parentTask p WHERE LOWER(t.taskId) = LOWER(:taskId)")
	public Task findByParent(@Param("taskId") String taskId);
}
