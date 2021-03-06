/**
 * 
 */
package com.cts.capsule.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.capsule.taskmanager.bean.ParentTask;

/**
 * Repository for Parent Tasks
 *
 */
@Repository
@Transactional
public interface ParentTaskRepository extends JpaRepository<ParentTask, Integer> {

}
