package net.springboot.javaguids.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springboot.javaguids.models.Employee;

@Repository
public interface EmployeRepository extends JpaRepository<Employee, Long>{

}
