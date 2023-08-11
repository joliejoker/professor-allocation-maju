package com.project.professor.allocation.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@Entity
public class Professor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty(access=Access.READ_ONLY)
	@Schema(accessMode=AccessMode.READ_ONLY)
	private Long id;
	private String name;
	@Column(unique=true,nullable=true)
	@Schema(example="012.345.678-90",type="string")
	private String cpf;
	
	@Column(name="FK_dpt_id",nullable=false)
	@JsonProperty(access=Access.WRITE_ONLY)
	@Schema(accessMode=AccessMode.WRITE_ONLY)
	private Long departmentId;
	@ManyToOne(optional = false)
	@JoinColumn(name="FK_dpt_id",nullable=false,insertable=false,updatable=false)
	@JsonProperty(access=Access.READ_ONLY)
	@Schema(accessMode=AccessMode.READ_ONLY,allOf=Course.class)
	private Department department;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}
	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	/**
	 * @return the departmentId
	 */
	public Long getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Professor [id=" + id + ", name=" + name + ", cpf=" + cpf + ", departmentId=" + departmentId
				+ ", department=" + department + "]";
	}
	
}
