package com.vti.spring.entity.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountFilter {
	
	private String email;
	
	private String username;
}
