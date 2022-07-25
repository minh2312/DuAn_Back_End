package ml.duAn.api.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ml.duAn.api.dao.IEmployeesDAO;
import ml.duAn.api.entities.Employees;

@Repository
public class EmployeesDAOImpl implements IEmployeesDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Employees> getAll() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Employees").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employees getById(Integer id) {
		Session session = sessionFactory.openSession();
		try {
			Employees employees = session.get(Employees.class, id);
			return employees;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean add(Employees t) {
		Session session = sessionFactory.openSession();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date time = c.getTime();
			t.setUpdateDate(time);
			session.beginTransaction();
			session.save(t);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.beginTransaction().rollback();
		}
		return false;
	}

	@Override
	public boolean edit(Employees t) {
		Session session = sessionFactory.openSession();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date time = c.getTime();
			t.setUpdateDate(time);
			session.beginTransaction();
			session.update(t);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.beginTransaction().rollback();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.delete(getById(id));
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.beginTransaction().rollback();
		}
		return false;
	}

	@Override
	public List<Employees> getByName(String name) {
		Session session = sessionFactory.openSession();
		try {
			if(name!=null && !name.isEmpty()) {
				name = "%"+name+"%";
			}else {
				name = "%";
			}
			List list = session.createQuery("from Employees WHERE nameEmployee LIKE :name")
								.setParameter("name", name)
								.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employees> sortByDESC(String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employees> sortByASC(String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employees getByImage(String image) {
		Session session = sessionFactory.openSession();
		try {
			Employees uniqueResult = (Employees) session.createQuery("from Employees WHERE image LIKE :image")
								.setParameter("image", image)
								.uniqueResult();
			return uniqueResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
