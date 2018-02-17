package by.it.company.demoSpringHibernate.services;

import by.it.company.demoSpringHibernate.exceptions.ServicesException;
import by.it.company.demoSpringHibernate.models.EmployeeModel;

import java.util.List;
import java.util.Optional;

public interface IFacadeServices {

    // employee
    EmployeeModel addNewEmployee(EmployeeModel newEmployee) throws ServicesException;

    List getEmployeesList() throws ServicesException;

    Optional<EmployeeModel> getEmployee(Long idEmployee) throws ServicesException;

    boolean updateEmployee(Long idEmployee, EmployeeModel employeeModel) throws ServicesException;

    boolean deleteEmployee(Long idEmployee) throws ServicesException;
}
