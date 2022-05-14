package com.vti.spring.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdate {

	@NotBlank(message = "The email mustn't be null value")
	@Length(max = 50, message = "The email's length is max 50 characters")
	@Email(message = "Please provide a valid email address")
	private String email;

	@NotBlank(message = "The password mustn't be null value")
	@Length(max = 800, message = "The password's length is max 800 characters")
	private String password;

	@NotBlank(message = "The firstName mustn't be null value")
	@Length(max = 50, message = "The firstName's length is max 50 characters")
	private String firstName;

	@NotBlank(message = "The lastName mustn't be null value")
	@Length(max = 50, message = "The lastName's length is max 50 characters")
	private String lastName;

	private int departmentId;
}
