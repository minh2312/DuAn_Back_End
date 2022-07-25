package ml.duAn.api.dao;

import java.util.List;

public interface IGenneralDAO<T,ID> {
	public List<T> getAll();
	
	
	
	public T getById(ID id);
	
	
	
	public boolean add(T t);
	
	
	
	public boolean edit(T t);
	
	
	
	public boolean delete(ID id);
	
	
	public List<T> getByName(String name);
	
	
	
	
	public List<T> sortByDESC(String order);
	
	
	
	
	public List<T> sortByASC(String order);
}
