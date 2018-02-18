package by.it.company.demoSpringHibernate.dao.interfaces;

import by.it.company.demoSpringHibernate.dao.entities.User;
import by.it.company.demoSpringHibernate.exceptions.DaoException;

import java.io.Serializable;

public interface IUserDao extends IDao<User, Serializable>{

    public User getUserByLogin(String login) throws DaoException;
}
