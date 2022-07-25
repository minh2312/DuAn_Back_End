package ml.duAn.api.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ml.duAn.api.dao.IDepartmentDAO;
import ml.duAn.api.dao.IEmployeesDAO;
import ml.duAn.api.dao.ILevelsDAO;

@RestController
public class General {
	@Autowired
	 IDepartmentDAO _IDepartmentDAO;
	@Autowired
	 ILevelsDAO _ILevelsDAO;
	@Autowired
	IEmployeesDAO _IEmployeesDAO;
	
	
}
