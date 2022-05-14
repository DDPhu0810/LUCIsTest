package com.vti.spring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Account")
public class Account {
	
	@Id
	@Column(name = "AccountID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;

	@Column(name = "Email", nullable = false, unique = true, length = 50)
	private String email;

	@Column(name = "Username", nullable = false, unique = true, length = 50)
	private String username; 
	
	@Column(name = "Password", nullable = false, length = 800)
	private String password;

	@Column(name = "FirstName", nullable = false, length = 50)
	private String firstName;

	@Column(name = "LastName", nullable = false, length = 50)
	private String lastName;
	
	@Formula("concat(FirstName, ' ', LastName)")
	private String fullName;

	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createDate = new Date();
	
	@ManyToOne
	@JoinColumn(name = "DepartmentID", nullable = false)
	private Department department;
}
