package by.it.company.demoSpringHibernate.services.managers.impl;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import by.it.company.demoSpringHibernate.dao.repositories.EmployeeRepository;
import by.it.company.demoSpringHibernate.exceptions.ServicesException;
import by.it.company.demoSpringHibernate.models.EmployeeModel;
import by.it.company.demoSpringHibernate.services.managers.interfaces.IEmployeeManager;
import by.it.company.demoSpringHibernate.services.managers.interfaces.IUtilsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeManagerImpl2 implements IEmployeeManager {

    private Logger logger = Logger.getLogger(EmployeeManagerImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    IUtilsService utilsService;

    /**
     * method save new employee into database
     *
     * @param newEmployee - employee model
     * @return - new employee model with id
     */
    @Override
    public Optional<EmployeeModel> addNewEmployee(EmployeeModel newEmployee) {
        Employee employee = employeeRepository.save(new Employee(newEmployee.firstName, newEmployee.surname));
        return utilsService.createEmployeeModel(employee);
    }

    /**
     * Method get list employees
     *
     * @return employee model list
     */
    @Override
    public List getEmployeesList() {
        List employees = (List) employeeRepository.findAll();
        return utilsService.createEmployeeModelList(employees);
    }

    /**
     * Method get employee info by employee id
     *
     * @param idEmployee - employee id
     * @return if employee is exist returnt optional with EmployeeModel,
     * @throws ServicesException - if if employee isn`t exist return
     */
    @Override
    public Optional <EmployeeModel> getEmployee(Long idEmployee) throws ServicesException {

        Optional<Employee> employee = employeeRepository.findById(idEmployee);
        return Optional.of(employee.flatMap(utilsService::createEmployeeModel)
                .orElseThrow(() -> new ServicesException("such item was not found")));
    }

    /**
     * method get list employees with like surname
     *
     * @param surname - employee surname
     * @return - list with employee models or empty list
     */
    @Override
    public List getEmployee(String surname) {
        List employees = employeeRepository.getEmployeeBySurname(surname);
        return utilsService.createEmployeeModelList(employees);
    }

    /**
     * method update employee info
     *
     * @param idEmployee    - employee id who will be changed
     * @param employeeModel - new employee info
     * @return
     * @throws ServicesException if such a employee does not exist
     */
    @Override
    public void updateEmployee(Long idEmployee, EmployeeModel employeeModel) throws ServicesException {
        Optional<Employee> employeeOpt = employeeRepository.findById(idEmployee);
        Employee employee = employeeOpt.orElseThrow(() -> new ServicesException("such a employee does not exist"));
        employee.setFirstName(employeeModel.firstName);
        employee.setSurname(employee.getSurname());
        employeeRepository.save(employee);
    }

    /**
     * method delete employee by id
     *
     * @param idEmployee - id employee who will be deleted
     * @return true - if delete ok, false - if delete has exception
     * @throws ServicesException
     */
    @Override
    public boolean deleteEmployee(Long idEmployee) {
        employeeRepository.deleteById(idEmployee);
        return true;

    }


}
