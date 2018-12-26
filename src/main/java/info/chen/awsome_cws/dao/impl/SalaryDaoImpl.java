package info.chen.awsome_cws.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import info.chen.awsome_cws.dao.AbstractDao;
import info.chen.awsome_cws.dao.SalaryDao;
import info.chen.awsome_cws.entity.Salary;

@Repository
@Transactional
public class SalaryDaoImpl extends AbstractDao<Salary> implements SalaryDao {

	@Override
	public List<Salary> getSalariesByEmployeeID(Integer id) {
		Query query = getQuery("FROM Salary WHERE emp_no=:emp_no");
		query.setInteger("emp_no", id);
		return query.list();
	}

}