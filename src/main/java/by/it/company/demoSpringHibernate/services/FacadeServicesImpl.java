package by.it.company.demoSpringHibernate.services;

import by.it.company.demoSpringHibernate.exceptions.ServicesException;
import by.it.company.demoSpringHibernate.models.EmployeeModel;
import by.it.company.demoSpringHibernate.services.managers.interfaces.IEmployeeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FacadeServicesImpl implements IFacadeServices {

    @Autowired
    IEmployeeManager employeeService;

    @Override
    public EmployeeModel addNewEmployee(EmployeeModel newEmployee) throws ServicesException {
        if (newEmployee == null) throw new ServicesException("object can not be empty");
        return employeeService.addNewEmployee(newEmployee);
    }

    @Override
    public List getEmployeesList() throws ServicesException {
        return employeeService.getEmployeesList();
    }

    @Override
    public EmployeeModel getEmployee(Long idEmployee) throws ServicesException {
        return employeeService.getEmployee(idEmployee);
    }

    @Override
    public void updateEmployee(Long idEmployee, EmployeeModel employeeModel) throws ServicesException {
        employeeService.updateEmployee(idEmployee, employeeModel);
    }

    @Override
    public void deleteEmployee(Long idEmployee) throws ServicesException {
        employeeService.deleteEmployee(idEmployee);
    }

    @Override
    public List getEmployee(String surname) throws ServicesException {
        return employeeService.getEmployee(surname);
    }
}
