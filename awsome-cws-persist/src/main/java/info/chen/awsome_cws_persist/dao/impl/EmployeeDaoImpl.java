package info.chen.awsome_cws_persist.dao.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import info.chen.awsome_cws_persist.dao.AbstractDao;
import info.chen.awsome_cws_persist.dao.EmployeeDao;
import info.chen.awsome_cws_persist.entity.Department;
import info.chen.awsome_cws_persist.entity.DepartmentEmployee;
import info.chen.awsome_cws_persist.entity.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Employee> implements EmployeeDao{

	@Override
	public Employee findEmployeeByEmpID(Integer id) {
		Employee employee = (Employee) getSession().get(Employee.class, id);
		return employee;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployees(Integer limitNum) {
		Criteria critria = getCriteria();
		if(limitNum != 0) {
			critria.setMaxResults(limitNum);
		}
		return critria.list();
	}

	@Override
	public void addEmployee(Employee employee) {
		persistEntity(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		updateEntity(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		deleteEntity(employee);
	}

	@Override
	public Set<Department> getDepartmentsByEmployeeID(Integer id) {
		Employee employee = findEmployeeByEmpID(id);
		Set<DepartmentEmployee> deSet = employee.getDepartmentEmployees();
		Set<Department> departments = new HashSet<Department>();
		Iterator<DepartmentEmployee> iterator = deSet.iterator();
		while(iterator.hasNext()) {
			departments.add(iterator.next().getDepartment());
		}
		return departments;
	}
	
}