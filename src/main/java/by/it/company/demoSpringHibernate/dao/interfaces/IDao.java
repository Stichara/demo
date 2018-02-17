package by.it.company.demoSpringHibernate.dao.interfaces;

import by.it.company.demoSpringHibernate.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;

public interface IDao<T, PK extends Serializable> {

    List<T> getAll() throws DaoException;

    T get(PK id) throws DaoException;

    PK add(T object) throws DaoException;

    void update(T object) throws DaoException;

    void delete(T object) throws DaoException;
}