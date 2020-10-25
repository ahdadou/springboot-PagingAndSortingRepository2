package net.springboot.javaguids.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort;
import net.springboot.javaguids.models.Employee;
import net.springboot.javaguids.repository.EmployeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
 
	@Autowired
	EmployeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public void savedEmployee(Employee employe) {
		// TODO Auto-generated method stub
		employeeRepository.save(employe);
		
	}

	@Override
	public Employee getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id).get();
	}

	@Override
	public void deleteEmployeeById(Long id) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(id);
		
	}

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize,String sortField,String sortDirec) {
		Sort sort =sortDirec.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortField).ascending():
			Sort.by(sortField).descending();
		
		Pageable pageable=PageRequest.of(pageNo - 1, pageSize,sort);
		// TODO Auto-generated method stub
		return this.employeeRepository.findAll(pageable);
	}

}
