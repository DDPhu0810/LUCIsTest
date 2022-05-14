package com.vti.spring.dto;

import java.util.Date;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDto {
	
	private int accountId;
	
	private String username;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	@Formula("concat(firstName, ' ', lastName)")
	private String fullName;
	
	@JsonProperty("DepartmentName")
	private String departmentDepartmentName;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createDate;
}
