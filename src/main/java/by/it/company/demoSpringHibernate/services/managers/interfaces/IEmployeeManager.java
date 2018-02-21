package by.it.company.demoSpringHibernate.services.managers.interfaces;

import by.it.company.demoSpringHibernate.exceptions.ServicesException;
import by.it.company.demoSpringHibernate.models.EmployeeModel;

import java.util.List;
import java.util.Optional;

public interface IEmployeeManager {

    Optional<EmployeeModel> addNewEmployee(EmployeeModel newEmployee) throws ServicesException;

   Optional<EmployeeModel> getEmployee(Long idEmployee) throws ServicesException;

    List getEmployeesList() throws ServicesException;

    void updateEmployee(Long idEmployee, EmployeeModel employeeModel) throws ServicesException;

    boolean deleteEmployee(Long idEmployee) throws ServicesException;

    List getEmployee(String surname) throws ServicesException;
}
