package by.it.company.demoSpringHibernate.dao.interfaces;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import by.it.company.demoSpringHibernate.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;

public interface IEmployeeDao extends IDao<Employee, Serializable> {
    List getEmployeeBySurname(String surname) throws DaoException;
}
