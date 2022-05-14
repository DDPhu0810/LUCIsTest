package com.vti.spring.businesslayer;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.spring.dto.AccountCreate;
import com.vti.spring.dto.AccountUpdate;
import com.vti.spring.entity.Account;
import com.vti.spring.entity.filter.AccountFilter;

public interface IAccountService {

	Page<Account> get(AccountFilter accountFilter, Pageable pageable);
	
	void create(@Valid AccountCreate accountCreate);
	
	void update(AccountUpdate accountUpdate, AccountFilter accountFilter) throws Exception;
	
	void delete(AccountFilter accountFilter) throws Exception;

}
