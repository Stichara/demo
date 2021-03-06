package by.it.company.demoSpringHibernate.services.managers.interfaces;

import by.it.company.demoSpringHibernate.exceptions.ServicesException;
import by.it.company.demoSpringHibernate.models.EmployeeModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeManager {

    EmployeeModel addNewEmployee(EmployeeModel newEmployee) throws ServicesException;

    EmployeeModel getEmployee(Long idEmployee) throws ServicesException;

    List getEmployeesList() throws ServicesException;

    List getEmployeesList(Pageable pageable) throws ServicesException;

    void updateEmployee(Long idEmployee, EmployeeModel employeeModel) throws ServicesException;

    void deleteEmployee(Long idEmployee) throws ServicesException;

    List getEmployee(String surname) throws ServicesException;
}
