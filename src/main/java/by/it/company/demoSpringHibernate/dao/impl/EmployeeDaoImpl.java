package by.it.company.demoSpringHibernate.dao.impl;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import by.it.company.demoSpringHibernate.dao.interfaces.IEmployeeDao;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Repository
@Transactional
public class EmployeeDaoImpl  extends AbstractDaoImpl<Employee,Serializable> implements IEmployeeDao{

    public EmployeeDaoImpl() {
        super(Employee.class);
    }
}
