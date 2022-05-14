package com.vti.spring.datalayer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.vti.spring.entity.Account;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Integer>{

	boolean existsByusername(String username);

	Account findByusername(String username);

	boolean existsByemail(String email);

	Account findByemail(String email);

	Page<Account> findAll(Specification<Account> where, Pageable pageable);

}
