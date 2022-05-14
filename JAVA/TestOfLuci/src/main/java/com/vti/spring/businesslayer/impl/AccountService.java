package com.vti.spring.businesslayer.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vti.spring.businesslayer.IAccountService;
import com.vti.spring.datalayer.AccountRepository;
import com.vti.spring.datalayer.DepartmentRepository;
import com.vti.spring.dto.AccountCreate;
import com.vti.spring.dto.AccountUpdate;
import com.vti.spring.entity.Account;
import com.vti.spring.entity.Department;
import com.vti.spring.entity.filter.AccountFilter;
import com.vti.spring.specification.AccountSpecification;

@Service
public class AccountService implements IAccountService{
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public Page<Account> get(AccountFilter accountFilter,
			Pageable pageable
			) {
		
		Specification<Account> where = AccountSpecification.buildWhere(accountFilter);
		return accountRepository.findAll(where, pageable);
	}
	
	
	@Override
	public void create(@Valid AccountCreate accountCreate) {
		accountCreate.setPassword(new BCryptPasswordEncoder().encode(accountCreate.getPassword()));
		Account account = modelMapper.map(accountCreate, Account.class);
		Department department = departmentRepository.findBydepartmentId(accountCreate.getDepartmentId());
		account.setDepartment(department);
		SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (fomat.parse(accountCreate.getCreateDate()) != null) {
				Date createDate = fomat.parse(accountCreate.getCreateDate());

				account.setCreateDate(createDate);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		accountRepository.save(account);
	}
	
	@Override
	public void update(AccountUpdate accountUpdate, AccountFilter accountFilter) throws Exception {
		if (accountFilter.getUsername() != null && accountRepository.findByusername(accountFilter.getUsername()) != null) {
			Account account = accountRepository.findByusername(accountFilter.getUsername());
			account.setFirstName(accountUpdate.getFirstName());
			account.setLastName(accountUpdate.getLastName());
			account.setEmail(accountUpdate.getEmail());
			accountUpdate.setPassword(new BCryptPasswordEncoder().encode(accountUpdate.getPassword()));
			account.setPassword(accountUpdate.getPassword());
			Department department = departmentRepository.findBydepartmentId(accountUpdate.getDepartmentId());
			account.setDepartment(department);
			accountRepository.save(account);
		} else if (accountFilter.getEmail() != null && accountRepository.findByemail(accountFilter.getEmail()) != null) {
			Account account = accountRepository.findByemail(accountFilter.getEmail());
			account.setFirstName(accountUpdate.getFirstName());
			account.setLastName(accountUpdate.getLastName());
			account.setEmail(accountUpdate.getEmail());
			accountUpdate.setPassword(new BCryptPasswordEncoder().encode(accountUpdate.getPassword()));
			account.setPassword(accountUpdate.getPassword());
			Department department = departmentRepository.findBydepartmentId(accountUpdate.getDepartmentId());
			account.setDepartment(department);
			accountRepository.save(account);
		} else {
			throw new Exception();
		}
		
	}
	
	@Override
	public void delete(AccountFilter accountFilter) throws Exception {
		if (accountFilter.getUsername() != null && accountRepository.existsByusername(accountFilter.getUsername())) {
			Account account = accountRepository.findByusername(accountFilter.getUsername());
			accountRepository.delete(account);
		} else if (accountFilter.getEmail() != null && accountRepository.existsByemail(accountFilter.getEmail())) {
			Account account = accountRepository.findByemail(accountFilter.getEmail());
			accountRepository.delete(account);
		} else {
			throw new Exception();
		}
	}

	
}
