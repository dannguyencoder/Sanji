package com.loas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "law_type")
public class LawType {
	@Id
	@Column(name = "law_type_id")
	private String lawTypeId;
	@Column(name = "law_type_name")
	private String lawTypeName;
	@Column(name = "law_type_desc")
	private String lawTypeDesc;

	public String getLawTypeId() {
		return lawTypeId;
	}

	public void setLawTypeId(String lawTypeId) {
		this.lawTypeId = lawTypeId;
	}

	public String getLawTypeName() {
		return lawTypeName;
	}

	public void setLawTypeName(String lawTypeName) {
		this.lawTypeName = lawTypeName;
	}

	public String getLawTypeDesc() {
		return lawTypeDesc;
	}

	public void setLawTypeDesc(String lawTypeDesc) {
		this.lawTypeDesc = lawTypeDesc;
	}

}
