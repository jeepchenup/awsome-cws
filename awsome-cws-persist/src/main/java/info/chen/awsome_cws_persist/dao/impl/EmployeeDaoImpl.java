package info.chen.awsome_cws_persist.dao.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import info.chen.awsome_cws_persist.dao.AbstractDao;
import info.chen.awsome_cws_persist.dao.EmployeeDao;
import info.chen.awsome_cws_persist.entity.Department;
import info.chen.awsome_cws_persist.entity.DepartmentEmployee;
import info.chen.awsome_cws_persist.entity.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Employee> implements EmployeeDao{
	
	private static Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoImpl.class);
	
	@Override
	public Employee getEmployeeByEmpID(Integer id) {
		LOGGER.info("DAO -> Query employee {}", id);
		Employee employee = (Employee) getSession().get(Employee.class, id);
		return employee;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees(Integer limitNum) {
		
		List<Employee> employees = null;
		Criteria critria = getCriteria();
		if(limitNum != 0) {
			critria.setMaxResults(limitNum);
		}
		employees = critria.list();
		
		LOGGER.info("DAO -> Get All Employees, total size is {}", employees.size());
		return employees;
	}

	@Override
	public void addEmployee(Employee employee) {
		LOGGER.info("DAO -> Add Employee: {}", employee);
		persistEntity(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		LOGGER.info("DAO -> Update Employee : {}", employee);
		updateEntity(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		LOGGER.info("DAO -> Delete Employee : {}", employee);
		deleteEntity(employee);
	}

	@Override
	public Set<Department> getDepartmentsByEmployeeID(Integer id) {
		Employee employee = getEmployeeByEmpID(id);
		Set<DepartmentEmployee> deSet = employee.getDepartmentEmployees();
		Set<Department> departments = new HashSet<Department>();
		Iterator<DepartmentEmployee> iterator = deSet.iterator();
		while(iterator.hasNext()) {
			departments.add(iterator.next().getDepartment());
		}
		LOGGER.info("DAO -> {} employee works for [{}]", id, departments);
		return departments;
	}
	
}
