package ml.duAn.api.dao;

import ml.duAn.api.entities.Employees;

public interface IEmployeesDAO extends IGenneralDAO<Employees, Integer>{
	public Employees getByImage(String image);
	
}
