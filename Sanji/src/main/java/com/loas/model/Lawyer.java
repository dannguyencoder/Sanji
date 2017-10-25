package com.loas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.Length;

@Entity
@Indexed
@Table(name = "lawyer")
public class Lawyer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lawyer_id")
	private int lawyer_id;
	
	@Field
	@NotNull
	@Length(min = 3, max = 30,message="*First name's length is between 3 and 30")
	@Column(name = "lawyer_firstname")
	private String lawyer_firstname;
	
	@Field
	@NotNull
	@Length(min=3,max=30,message="*Last name's length is between 3 and 30")
	@Column(name = "lawyer_lastname")
	private String lawyer_lastname;
	
	@NotNull
	@Column(name = "dateofbirth")
	private String dateofbirth;
	
	@NotNull
	@Column(name = "gender")
	private String gender;
	
	@NotNull
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="*Please provide a valid email address")
	@Column(name = "email")
	private String email;
	
	@Field
	@NotNull
	@Column(name = "phone")
	private String phone;
	
	@NotNull
	@Length(min = 5,message="*Address's min length is 5")
	@Column(name = "address")
	private String address;
	
	@Column(name = "join_date")
	private String join_date;
	
	@NotNull
	@Column(name = "law_type_id")
	private String law_type_id;
	
	@Column(name = "certification")
	private String certification;
	
	@NotNull
	@Column(name = "salary_id")
	private String salary_id;
	
	
	public int getLawyer_id() {
		return lawyer_id;
	}

	public void setLawyer_id(int lawyer_id) {
		this.lawyer_id = lawyer_id;
	}

	public String getLawyer_firstname() {
		return lawyer_firstname;
	}

	public void setLawyer_firstname(String lawyer_firstname) {
		this.lawyer_firstname = lawyer_firstname;
	}

	public String getLawyer_lastname() {
		return lawyer_lastname;
	}

	public void setLawyer_lastname(String lawyer_lastname) {
		this.lawyer_lastname = lawyer_lastname;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
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

	public String getJoin_date() {
		return join_date;
	}

	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}

	public String getLaw_type_id() {
		return law_type_id;
	}

	public void setLaw_type_id(String law_type_id) {
		this.law_type_id = law_type_id;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getSalary_id() {
		return salary_id;
	}

	public void setSalary_id(String salary_id) {
		this.salary_id = salary_id;
	}

}
