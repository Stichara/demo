package by.it.company.demoSpringHibernate.services.managers.interfaces;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import by.it.company.demoSpringHibernate.exceptions.ServicesException;
import by.it.company.demoSpringHibernate.models.EmployeeModel;

import java.util.List;

public interface IUtilsService {
    EmployeeModel createEmployeeModel(Long id, Employee employee) throws ServicesException;

    EmployeeModel createEmployeeModel(Employee employee) throws ServicesException;

    List createEmployeeModelList(List<Employee> employees) throws ServicesException;


}
