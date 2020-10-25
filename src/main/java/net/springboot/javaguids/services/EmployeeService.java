package net.springboot.javaguids.services;

import java.util.List;

import org.springframework.data.domain.Page;

import net.springboot.javaguids.models.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployee();
	void savedEmployee(Employee employe);
	Employee getEmployeeById(Long id);
	void deleteEmployeeById(Long id);
	Page<Employee> findPaginated(int pageNo,int pageSize,String sortField,String sortDirec);

}
