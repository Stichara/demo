package by.it.company.demoSpringHibernate.dao.impl;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import by.it.company.demoSpringHibernate.dao.interfaces.IEmployeeDao;
import by.it.company.demoSpringHibernate.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Repository
@Transactional
public class EmployeeDaoImpl  extends AbstractDaoImpl<Employee,Serializable> implements IEmployeeDao{

    public EmployeeDaoImpl() {
        super(Employee.class);
    }

    @Override
    public List getEmployeeBySurname(String surname) throws DaoException {
        String hql = "From Employee where surname = :surnameParam";
        try {
            Query query = getQuery(hql);
            query.setParameter("surnameParam", surname);
            return query.list();
        }catch (HibernateException e){
            throw new DaoException(e);
        }
    }
}
