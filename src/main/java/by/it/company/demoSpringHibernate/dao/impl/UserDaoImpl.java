package by.it.company.demoSpringHibernate.dao.impl;

import by.it.company.demoSpringHibernate.dao.entities.User;
import by.it.company.demoSpringHibernate.dao.interfaces.IUserDao;
import by.it.company.demoSpringHibernate.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

//import javax.transaction.Transactional;
import java.io.Serializable;

@Repository
//@Transactional
public class UserDaoImpl extends AbstractDaoImpl<User, Serializable> implements IUserDao {

    public UserDaoImpl(){
        super(User.class);
    }

    /**
     * method get gives information about user by login
     * @param login - user login
     * @return if user is exist return User object
     *          if user isn`t exist return null
     * @throws DaoException
     */
    @Override
    public User getUserByLogin(String login) throws DaoException {
        String hql = "From User where login = :loginParam";
        try {
            Query query = getQuery(hql);
            query.setParameter("loginParam", login);
            return (User) query.uniqueResult();
        }catch (HibernateException e){
            throw new DaoException(e);
        }
    }
}
