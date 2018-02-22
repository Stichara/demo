package by.it.company.demoSpringHibernate.services.managers.impl;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import by.it.company.demoSpringHibernate.dao.entities.User;
import by.it.company.demoSpringHibernate.exceptions.ServicesException;
import by.it.company.demoSpringHibernate.models.EmployeeModel;
import by.it.company.demoSpringHibernate.models.UserModel;
import by.it.company.demoSpringHibernate.services.managers.interfaces.IUtilsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UtilsServiceImpl implements IUtilsService {

    /**
     * Method create employee model from employee entity and id
     * @param id - new employee id
     * @param employee - employee entity with first name and surname
     * @return EmployeeModel
     * @throws ServicesException
     */
    @Override
    public EmployeeModel createEmployeeModel(Long id, Employee employee) throws ServicesException{
        if ((employee == null)|| (id == null))
            throw  new ServicesException("Error create model saved employee: parametres can not be null: id ="+id+" employee:"+ employee.toString() );
        return new EmployeeModel(id, employee.getFirstName(), employee.getSurname());
    }

    /**
     * Method create employee model from employee entity
     * @param employee - employee entity with first name and surname
     * @return Optional with EmployeeModel
     * @throws ServicesException
     */
    @Override
    public Optional<EmployeeModel> createEmployeeModel(Employee employee) {
//        if (employee == null) throw new ServicesException("Error create model employee: parameter can not be null");
        return Optional.of(
                new EmployeeModel(employee.getId(),employee.getFirstName(),employee.getSurname())
        );
    }

    /**
     * Method create employees model list from employee entity list
     * @param employees - employees entity list
     * @return - EmployeeModel list
     * @throws ServicesException
     */
    @Override
    public List<EmployeeModel> createEmployeeModelList(List<Employee> employees){
        return employees.stream()
                .map(employee -> new EmployeeModel(employee.getId(),employee.getFirstName(),employee.getSurname()))
                .collect(Collectors.toList());
    }

    /**
     * Method create user model from user entity
     * @param user - user entity with information
     * @return UserModel with information
     */
    @Override
    public Optional<UserModel> createUserModel(User user) {
//        if (user == null) throw new ServicesException("Error create user model: parameter can not be null");
        return Optional.of(new UserModel(user.getLogin(),user.getPswd(),user.getRole().getName()));
    }
}
