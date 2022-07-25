package ml.duAn.api.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ml.duAn.api.dao.IDepartmentDAO;
import ml.duAn.api.entities.Department;
import ml.duAn.api.model.GetCurDate;
import ml.duAn.api.model.CRUD;

@Repository
public class DepartmentDAOImpl implements IDepartmentDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Department> getAll() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Department Where status = 1").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Department getById(Integer id) {
		Session session = sessionFactory.openSession();
		try {
			Department department = session.get(Department.class, id);
			return department;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean add(Department t) {
		GetCurDate curDate = new GetCurDate();
		t.setUpdateDate(curDate.getDateTime());
		CRUD<Department> update = new CRUD<Department>();
		boolean bl = update.add(t, sessionFactory);
		return bl;
	}

	@Override
	public boolean edit(Department t) {
		GetCurDate curDate = new GetCurDate();
		t.setUpdateDate(curDate.getDateTime());
		CRUD<Department> update = new CRUD<Department>();
		boolean bl = update.edit(t, sessionFactory);
		return bl;
	}

	@Override
	public boolean delete(Integer id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Department dep = session.get(Department.class, id);
			session.delete(dep);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return false;
	}

	@Override
	public List<Department> getByName(String name) {
		Session session = sessionFactory.openSession();
		try {
			if(name!=null && !name.isEmpty()) {
				name = "%"+name+"%";
			}else {
				name = "%";
			}
			List list = session.createQuery("from Department WHERE nameDepartment LIKE :name")
								.setParameter("name", name)
								.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Department> sortByDESC(String order) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Department  WHERE status = 1 ORDER BY "+order+" DESC").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Department> sortByASC(String order) {
		Session session = sessionFactory.openSession();
		try {System.out.println();
			List list = session.createQuery("from Department  ORDER BY "+order+" ASC").list();
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateStatus(Department d) {
		Session session = sessionFactory.openSession();
		try {
			GetCurDate curDate = new GetCurDate();
			d.setUpdateDate(curDate.getDateTime());
			session.beginTransaction();
			// session.createQuery("UPDATE Department SET status = :status").setParameter("status", d.getStatus()).executeUpdate();
			d.setStatus(false);
			session.update(d);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return false;
	}

	@Override
	public List<Department> history() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Department Where status = 0").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateMultipleById(Integer id) {

		return false;
	}




}
