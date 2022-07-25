package ml.duAn.api.dao;

import java.util.List;

import ml.duAn.api.entities.Levels;

public interface ILevelsDAO extends IGenneralDAO<Levels, Integer>{
	public List<Levels> paginationLevel(int page,int limit);
	public boolean updateStatus(Levels d);
    public boolean updateMultipleById(Integer id);
    public List<Levels> history();
}
