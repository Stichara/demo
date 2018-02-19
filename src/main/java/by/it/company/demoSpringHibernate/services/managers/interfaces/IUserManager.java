package by.it.company.demoSpringHibernate.services.managers.interfaces;

import by.it.company.demoSpringHibernate.models.UserModel;

public interface IUserManager {

    public UserModel getUser(String login);

}
