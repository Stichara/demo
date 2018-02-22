package by.it.company.demoSpringHibernate.dao.impl;

import by.it.company.demoSpringHibernate.dao.interfaces.IDao;
import by.it.company.demoSpringHibernate.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

//import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

//@Transactional
public class AbstractDaoImpl<T, PK extends Serializable> implements IDao<T, PK> {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Class<T> clazz;

    public AbstractDaoImpl() {
        super();
    }

    public AbstractDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<T> getAll() throws DaoException {
        List<T> list = null;
        try {
            list = getSession().createCriteria(clazz).list();
            return list;
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public T get(PK id) throws DaoException {

        try {
            T object = (T) getSession().get(clazz, id);
            return object;
        }  catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PK add(T object) throws DaoException {

        PK id = null;
        try {
            id = (PK) getSession().save(object);
            return (PK) id;
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(T object) throws DaoException {

        try {
            getSession().update(object);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(T object) throws DaoException {

        try {
            getSession().delete(object);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    protected Query getQuery(String hql) throws DaoException {
        try {
            Query query = getSession().createQuery(hql);
            return query;
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }
}
