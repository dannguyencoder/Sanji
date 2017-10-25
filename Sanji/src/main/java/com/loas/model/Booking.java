package com.loas.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Indexed
@Table(name="booking")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="booking_id")
	private int bookingId;
	
	@Field(store = Store.NO)
	@Length(min=3,max=30,message="*Last name's length is between 3 and 30")
	@Column(name="cust_lastname")
	private String lastName;
	
	@Field
	@Length(min = 3, max = 30,message="*First name's length is between 3 and 30")
	@Column(name="cust_firstname")
	private String firstName;
	
	@Column(name="dateofbirth")
	private String dob;
	
	@NotEmpty(message = "*Select your gender")
	@Column(name="gender")
	private String gender;
	
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="*Please provide a valid email address")
	@Column(name="email")
	private String email;
	
    @Field
	@Pattern(regexp="\\d{11}", message="*Please provide a valid phone number")
	@Column(name="phone")
	private String phone;
	
	@Length(min = 5,message="*Address's min length is 5")
	@Column(name="address")
	private String address;
	
	@Column(name="prefer_date")
	private String prefer_date;
	
	@Column(name="prefer_time")
	private String prefer_time;
	
	@Column(name="case_cat_id")
	private int case_cat_id;
	
	@Column(name="status",insertable=false)
	private String status;
	
	@Column(name="notes")
	private String note;
	
	@Column(name="time_booking", insertable=false, updatable=false)
	private String time;
	
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrefer_date() {
		return prefer_date;
	}
	public void setPrefer_date(String prefer_date) {
		this.prefer_date = prefer_date;
	}
	public String getPrefer_time() {
		return prefer_time;
	}
	public void setPrefer_time(String prefer_time) {
		this.prefer_time = prefer_time;
	}
	
	public int getCase_cat_id() {
		return case_cat_id;
	}
	public void setCase_cat_id(int case_cat_id) {
		this.case_cat_id = case_cat_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
