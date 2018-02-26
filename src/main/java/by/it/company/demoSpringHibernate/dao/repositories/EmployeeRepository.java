package by.it.company.demoSpringHibernate.dao.repositories;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    /**
     * find employees with similar surname
     * @param surname
     * @return - list employees
     */
    List findEmployeeBySurname(String surname);

    /**
     * method find employee by firstname and surname
     * @param surname
     * @param firstName
     * @return Employee object
     */
    @Query("select e from Employee  e where e.surname = ?1 and e.firstName = ?2")
    Employee findEmployeeBySurnameAndFirstName(String surname, String firstName);

    /**
     * method find employee use pagination
     * @param pageable - pagination parameters
     * @return list
     */
    Page<Employee> findAll(Pageable pageable);
}
