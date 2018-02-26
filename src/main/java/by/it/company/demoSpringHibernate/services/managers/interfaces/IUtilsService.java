package by.it.company.demoSpringHibernate.services.managers.interfaces;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import by.it.company.demoSpringHibernate.dao.entities.User;
import by.it.company.demoSpringHibernate.exceptions.ServicesException;
import by.it.company.demoSpringHibernate.models.EmployeeModel;
import by.it.company.demoSpringHibernate.models.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUtilsService {

    Optional<EmployeeModel> createEmployeeModel(Employee employee);

    List<EmployeeModel> createEmployeeModelList(List<Employee> employees);

    Optional<UserModel> createUserModel(User user);
}
