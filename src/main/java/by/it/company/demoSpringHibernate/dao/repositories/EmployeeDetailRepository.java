package by.it.company.demoSpringHibernate.dao.repositories;

import by.it.company.demoSpringHibernate.dao.entities.EmployeeDetail;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDetailRepository extends CrudRepository<EmployeeDetail, Long> {
}
