package by.it.company.demoSpringHibernate.dao.repositories;

import by.it.company.demoSpringHibernate.dao.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String > {
}
