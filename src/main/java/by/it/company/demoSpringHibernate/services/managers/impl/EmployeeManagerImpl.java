package by.it.company.demoSpringHibernate.services.managers.impl;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import by.it.company.demoSpringHibernate.dao.interfaces.IEmployeeDao;
import by.it.company.demoSpringHibernate.exceptions.DaoException;
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
public class EmployeeManagerImpl implements IEmployeeManager {

    private Logger logger = Logger.getLogger(EmployeeManagerImpl.class);

    @Autowired
    IEmployeeDao employeeDao;

    @Autowired
    IUtilsService utilsService;

    /**
     * method save new employee into database
     * @param newEmployee - employee model
     * @return - new employee model with id
     * @throws ServicesException - exceptions services layer
     */
    @Override
    public EmployeeModel addNewEmployee(EmployeeModel newEmployee) throws ServicesException {
        Employee employee = new Employee(newEmployee.firstName, newEmployee.surname);
        try {
            Long id = (Long)employeeDao.add(employee);
            return utilsService.createEmployeeModel(employee);
        } catch (DaoException e) {
            logger.error("[EmployeeServiceImpl/addNewEmployee] can not save new employee: "+employee.toString() + e.getLocalizedMessage());
            throw new ServicesException("Employee isn`t created");
        }
    }

    /**
     * Method get list employees
     * @return employee model list
     * @throws ServicesException
     */
    @Override
    public List<EmployeeModel> getEmployeesList() throws ServicesException {
        try {
            List employees = employeeDao.getAll();
            return utilsService.createEmployeeModelList(employees);
        } catch (DaoException e) {
            logger.error("[EmployeeServiceImpl/getEmployeesList] could not get a list of employees: " + e.getLocalizedMessage());
            throw new ServicesException("could not get a list of employees");
        }
    }

    /** Method get employee info by employee id
     *
     * @param idEmployee - employee id
     * @return if employee is exist returnt optional with EmployeerModel,
     *          if employee isn`t exist return empty optional
     */
    @Override
    public Optional<EmployeeModel> getEmployee(Long idEmployee) throws ServicesException{
        try{
            Employee employee = employeeDao.get(idEmployee);
            if (employee == null) return Optional.empty();
            return Optional.ofNullable(utilsService.createEmployeeModel(employee));
        }catch (DaoException e){
            logger.error("[EmployeeServiceImpl/getEmployee] could not get the employee by id:"+idEmployee+": " + e.getLocalizedMessage());
            throw new ServicesException("could not get a list of employees");
        }
    }

    @Override
    public boolean updateEmployee(Long idEmployee, EmployeeModel employeeModel) throws ServicesException {
        try{
            Employee employee = employeeDao.get(idEmployee);
            if (employee == null) return false;
            employee.setFirstName(employeeModel.firstName);
            employee.setSurname(employee.getSurname());
            employeeDao.update(employee);
            return true;
        }catch (DaoException e){
            logger.error("[EmployeeServiceImpl/getEmployee] could not update employee id:"+idEmployee+": " + e.getLocalizedMessage());
            throw new ServicesException("could not update the employees");
        }
    }

    @Override
    public boolean deleteEmployee(Long idEmployee) throws ServicesException{
        try{
            Employee employee = employeeDao.get(idEmployee);
            if (employee == null) return false;
            employeeDao.delete(employee);
        }catch (DaoException e){
            logger.error("[EmployeeServiceImpl/deleteEmployee] could not delete employee id:"+idEmployee+": " + e.getLocalizedMessage());
            throw new ServicesException("could not delete the employees");
        }
        return false;
    }
}
