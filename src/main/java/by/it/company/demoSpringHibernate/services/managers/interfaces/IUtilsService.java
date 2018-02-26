package by.it.company.demoSpringHibernate.services.managers.interfaces;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import by.it.company.demoSpringHibernate.dao.entities.User;
import by.it.company.demoSpringHibernate.exceptions.ServicesException;
import by.it.company.demoSpringHibernate.models.EmployeeModel;
import by.it.company.demoSpringHibernate.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IUtilsService {

    Optional<EmployeeModel> createEmployeeModel(Employee employee);

    List<EmployeeModel> createEmployeeModelList(List<Employee> employees);

    List<EmployeeModel> createEmployeeModelListFromPage(Page<Employee> page);

    Optional<UserModel> createUserModel(User user);

    Pageable createPageable(Integer page, Integer sizePage, Sort sort);
}
