package info.chen.awsome.cws.persist.dao;

import java.util.List;
import java.util.Set;

import info.chen.awsome.cws.persist.entity.Department;
import info.chen.awsome.cws.persist.entity.Employee;

public interface EmployeeDao {
	
	Employee getEmployeeByEmpID(Integer id);
	
	List<Employee> getAllEmployees(Integer limitNum);

	void addEmployee(Employee employee);
	
	void updateEmployee(Employee employee);
	
	void deleteEmployee(Employee employee);
	
	Set<Department> getDepartmentsByEmployeeID(Integer id);
}