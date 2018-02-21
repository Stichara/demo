package by.it.company.demoSpringHibernate.dao.repositories;

import by.it.company.demoSpringHibernate.dao.entities.Meeting;
import org.springframework.data.repository.CrudRepository;

public interface MeetingRepository extends CrudRepository<Meeting, Long > {
}
