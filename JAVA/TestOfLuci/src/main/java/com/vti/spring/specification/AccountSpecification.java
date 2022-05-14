package com.vti.spring.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import com.vti.spring.entity.Account;
import com.vti.spring.entity.filter.AccountFilter;

import lombok.RequiredArgsConstructor;

public class AccountSpecification {
	public static Specification<Account> buildWhere(AccountFilter accountFilter) {
		Specification<Account> where = null;

		if (accountFilter.getEmail() != null) {
			String email = accountFilter.getEmail().trim();
			CustomSpecification name = new CustomSpecification("email", email);
			where = Specification.where(name);
		}
		if (accountFilter.getUsername() != null) {
			String userName = accountFilter.getUsername().trim();
			CustomSpecification name = new CustomSpecification("userName", userName);
			where = Specification.where(name);
		}

		return where;

	}
}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<Account> {

	@NonNull
	private String field;
	@NonNull
	private Object value;

	@Override
	public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (field.equalsIgnoreCase("email")) {
			return criteriaBuilder.like(root.get("email"), "%" + value.toString() + "%");
		}
		if (field.equalsIgnoreCase("userName")) {
			return criteriaBuilder.like(root.get("userName"), "%" + value.toString() + "%");
		}

		return null;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
