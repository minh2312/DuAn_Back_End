package ml.duAn.api.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Level;
import ml.duAn.api.dao.ILevelsDAO;
import ml.duAn.api.entities.Levels;
import ml.duAn.api.model.CRUD;
import ml.duAn.api.model.GetCurDate;
import ml.duAn.api.model.Pagination;

@Repository
public class LevelsDAOImpl implements ILevelsDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@Override
	public List<Levels> getAll() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Levels WHERE status = 1").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	
	@Override
	public Levels getById(Integer id) {
		Session session = sessionFactory.openSession();
		try {
			Levels levels = session.get(Levels.class, id);
			return levels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean add(Levels t) {
		GetCurDate curDate = new GetCurDate();
		t.setUpdateDate(curDate.getDateTime());
		CRUD<Levels> crud = new CRUD<Levels>();
		boolean bl = crud.add(t, sessionFactory);
		return bl;
	}

	@Override
	public boolean edit(Levels t) {
		GetCurDate curDate = new GetCurDate();
		t.setUpdateDate(curDate.getDateTime());
		CRUD<Levels> crud = new CRUD<Levels>();
		boolean bl = crud.add(t, sessionFactory);
		return bl;
	}

	
	
	
	
	@Override
	public boolean delete(Integer id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			// session.createQuery("DELETE FROM Levels WHERE id = :id").setParameter("id",id).executeUpdate();
			session.delete(session.load(Levels.class, id));
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
	public List<Levels> getByName(String name) {
		Session session = sessionFactory.openSession();
		try {
			if(name != null && !name.isEmpty()) {
				name = "%" + name +"%";
			}else {
				name = "%";
			}
			List list = session.createQuery("from Levels WHERE name like :name ")
					.setParameter("name", name)
					.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	
	
	
	
	
	@Override
	public List<Levels> sortByDESC(String order) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Levels ORDER BY "+ order +" DESC").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	
	
	
	@Override
	public List<Levels> sortByASC(String order) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Levels ORDER BY "+ order +" ASC").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	
	
	
	@Override
	public List<Levels> paginationLevel(int page, int limit) {
		Session session = sessionFactory.openSession();
		try {
			Pagination p = new Pagination();
			p.setLimit(limit);
			p.setPage(page);
			List list = session.createQuery("from Levels")
								.setFirstResult(p.offSet())
								.setMaxResults(p.getLimit())
								.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}





	@Override
	public boolean updateStatus(Levels d) {
		Session session = sessionFactory.openSession();
		try {
			GetCurDate curDate = new GetCurDate();
			d.setUpdateDate(curDate.getDateTime());
			session.beginTransaction();
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
	public boolean updateMultipleById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}





	@Override
	public List<Levels> history() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Levels Where status = 0").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
