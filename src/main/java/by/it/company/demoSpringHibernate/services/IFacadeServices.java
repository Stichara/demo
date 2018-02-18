package by.it.company.demoSpringHibernate.services;

import by.it.company.demoSpringHibernate.exceptions.ServicesException;
import by.it.company.demoSpringHibernate.models.EmployeeModel;
import org.springframework.security.access.annotation.Secured;

import java.util.List;
import java.util.Optional;

public interface IFacadeServices {

    // employee methods
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    EmployeeModel addNewEmployee(EmployeeModel newEmployee) throws ServicesException;

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    List getEmployeesList() throws ServicesException;

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    Optional<EmployeeModel> getEmployee(Long idEmployee) throws ServicesException;

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    boolean updateEmployee(Long idEmployee, EmployeeModel employeeModel) throws ServicesException;

    @Secured({"ROLE_ADMIN"})
    boolean deleteEmployee(Long idEmployee) throws ServicesException;

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    List getEmployee(String surname) throws ServicesException;
}
