package by.it.company.demoSpringHibernate.dao.repositories;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("select e from employee e where e.surname = :surname")
    List getEmployeeBySurname(@Param("surname") String surname);

}
