package by.it.company.demoSpringHibernate.services.managers.interfaces;

import by.it.company.demoSpringHibernate.models.UserModel;

public interface UserManager {

    public UserModel getUser(String login);

}
