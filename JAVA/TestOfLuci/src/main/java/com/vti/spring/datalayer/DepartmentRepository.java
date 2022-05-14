package com.vti.spring.datalayer;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.vti.spring.entity.Department;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Integer>{
	
	Department findBydepartmentId(int departmentId);

}
