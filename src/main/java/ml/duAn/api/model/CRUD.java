package ml.duAn.api.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;


public class CRUD<T> {
    private T type;


    public boolean add(T t,SessionFactory sessionFactory){
        Session session =sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
			session.beginTransaction().rollback();
        }finally{
            session.close();
        }
        return false;
    }


    // update
    public boolean edit(T t,SessionFactory sessionFactory){
        Session session =sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
			session.beginTransaction().rollback();
        }finally{
            session.close();
        }
        return false;
    }


}
