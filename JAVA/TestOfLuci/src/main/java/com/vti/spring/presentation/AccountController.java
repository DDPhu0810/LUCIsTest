package com.vti.spring.presentation;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.spring.businesslayer.IAccountService;
import com.vti.spring.dto.AccountCreate;
import com.vti.spring.dto.AccountDto;
import com.vti.spring.dto.AccountUpdate;
import com.vti.spring.entity.Account;
import com.vti.spring.entity.filter.AccountFilter;

@RestController
@RequestMapping(value = "/api/v1/accounts")
@Validated
public class AccountController {
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	IAccountService accountService;
	
	@GetMapping()
	Page<AccountDto> get(AccountFilter accountFilter, Pageable pageable){
		Page<Account> pageAccount = accountService.get(accountFilter, pageable);

		List<AccountDto> listAccountDtos;
		
		listAccountDtos = modelMapper.map(pageAccount.getContent(), new TypeToken<List<AccountDto>>() {
		}.getType());
		
		Page<AccountDto> pageAccountDto = new PageImpl<>(listAccountDtos, pageable, pageAccount.getTotalElements());
		return pageAccountDto;
	}
	
	@PostMapping()
	ResponseEntity<?> create(@RequestBody @Valid AccountCreate accountCreate){
		accountService.create(accountCreate);
		return new ResponseEntity<>("Create Success", HttpStatus.OK);
	}
	
	@PutMapping()
	ResponseEntity<?> update(@RequestBody @Valid AccountUpdate accountUpdate, AccountFilter accountFilter){
		try {
			accountService.update(accountUpdate, accountFilter);
			return new ResponseEntity<>("Update Success", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Update Fail! Account Not Found!", HttpStatus.OK);

		}
	}
	
	@DeleteMapping
	ResponseEntity<?> delete(AccountFilter accountFilter){
		try {
			accountService.delete(accountFilter);
			return new ResponseEntity<>("Delete Success", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Delete Fail! Account Not Found!", HttpStatus.OK);

		}
	}
}
