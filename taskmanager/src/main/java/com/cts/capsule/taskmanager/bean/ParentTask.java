/**
 * 
 */
package com.cts.capsule.taskmanager.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author 269012
 *
 */
@Entity
@Table(name = "Parent_Task")
public class ParentTask implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "parent_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer parentId;
	@Column(name = "Parent_Task")
	private String parentTask;
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "parentTask")
	@JsonManagedReference
	private Task newTask;
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getParentTask() {
		return parentTask;
	}
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	public Task getNewTask() {
		return newTask;
	}
	public void setNewTask(Task newTask) {
		this.newTask = newTask;
	}
}
