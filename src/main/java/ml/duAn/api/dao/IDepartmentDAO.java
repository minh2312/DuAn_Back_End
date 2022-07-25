package ml.duAn.api.dao;

import java.util.List;

import ml.duAn.api.entities.Department;

public interface IDepartmentDAO extends IGenneralDAO<Department, Integer>{
    public boolean updateStatus(Department d);
    public boolean updateMultipleById(Integer id);
    public List<Department> history();
}
