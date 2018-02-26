package by.it.company.demoSpringHibernate.services.managers.impl;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import by.it.company.demoSpringHibernate.dao.entities.User;
import by.it.company.demoSpringHibernate.exceptions.ServicesException;
import by.it.company.demoSpringHibernate.models.EmployeeModel;
import by.it.company.demoSpringHibernate.models.UserModel;
import by.it.company.demoSpringHibernate.services.managers.interfaces.IUtilsService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UtilsServiceImpl implements IUtilsService {

    /**
     * Method create employee model from employee entity
     * @param employee - employee entity with first name and surname
     * @return Optional with EmployeeModel of Empty optional
     * @throws ServicesException
     */
    @Override
    public Optional<EmployeeModel> createEmployeeModel(Employee employee) {
        if (employee == null) return Optional.empty();
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
     * Method convert page with employees to employee models list
     * @param page - page containing employees list
     * @return - employee models list
     */
    @Override
    public List<EmployeeModel> createEmployeeModelListFromPage(Page<Employee> page) {
        return page.getContent()
                .stream()
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
        if (user == null) return Optional.empty();
        return Optional.of(new UserModel(user.getLogin(),user.getPswd(),user.getRole().getName()));
    }

    /**
     * method create Pageable
     * @param page - Page you want to retrieve.
     * @param sizePage - Size of the page you want to retrieve.
     * @param sort - sorting parameter
     * @return
     */
    @Override
    public Pageable createPageable(Integer page, Integer sizePage,  Sort sort) {
        return PageRequest.of(page,sizePage, sort);
    }
}
