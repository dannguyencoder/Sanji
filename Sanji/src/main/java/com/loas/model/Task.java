package com.loas.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;


@Entity
@Indexed
@Table(name="task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="task_id")
	private int task_id;
	
	@Column(name = "booking_id")
	private int booking_id;
	
	@Column(name = "prefered_date")
	private String prefered_date;
	
	@Column(name = "prefered_time")
	private String prefered_time;
	
	@Column(name = "lawyer_id")
	private int lawyer_id;
	
	@Column(name = "task_note")
	private String task_note;
	
	
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public String getPrefered_date() {
		return prefered_date;
	}
	public void setPrefered_date(String prefered_date) {
		this.prefered_date = prefered_date;
	}
	public String getPrefered_time() {
		return prefered_time;
	}
	public void setPrefered_time(String prefered_time) {
		this.prefered_time = prefered_time;
	}
	public int getLawyer_id() {
		return lawyer_id;
	}
	public void setLawyer_id(int lawyer_id) {
		this.lawyer_id = lawyer_id;
	}
	public String getTask_note() {
		return task_note;
	}
	public void setTask_note(String task_note) {
		this.task_note = task_note;
	}
	
	

}
