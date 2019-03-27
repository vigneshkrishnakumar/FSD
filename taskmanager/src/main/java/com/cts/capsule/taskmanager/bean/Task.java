/**
 * 
 */
package com.cts.capsule.taskmanager.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author 269012
 *
 */
@Entity
@Table(name = "Task")
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Task_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taskId;
	@NotNull
    @Size(max = 100)
	@Column(name = "Task")
	private String taskName;
	@NotNull
	@Column(name = "Start_Date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startDate;
	@NotNull
	@Column(name = "End_Date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date endDate;
	@NotNull
	@Column(name = "Priority")
	private int priority;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parent_id", referencedColumnName = "parent_id", nullable = false)
	@JsonBackReference
	private ParentTask parentTask;
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public ParentTask getParentTask() {
		return parentTask;
	}
	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}
}
