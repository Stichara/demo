package by.it.company.demoSpringHibernate.dao.repositories;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List findEmployeeBySurname(String surname);

    @Query("select e from Employee  e where e.surname = ?1 and e.firstName = ?2")
    Employee findEmployeeBySurnameAndFirstName(String surname, String firstName);

}
