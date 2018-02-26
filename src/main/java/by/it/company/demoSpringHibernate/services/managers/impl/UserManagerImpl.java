package by.it.company.demoSpringHibernate.services.managers.impl;

import by.it.company.demoSpringHibernate.dao.entities.User;
import by.it.company.demoSpringHibernate.dao.repositories.UserRepository;
import by.it.company.demoSpringHibernate.models.UserModel;
import by.it.company.demoSpringHibernate.services.managers.interfaces.IUserManager;
import by.it.company.demoSpringHibernate.services.managers.interfaces.IUtilsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserManagerImpl implements IUserManager {

    private Logger logger = Logger.getLogger(UserManagerImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUtilsService utilsService;

    /**
     * Method get user information by login
     * @param login - user login
     * @return if user is exist return user model with information
     *          if user isn`t exist return user model with empty fields
     */
    @Override
    public UserModel getUser(String login) {
        Optional<User> user = userRepository.findById(login);
        return user.flatMap(utilsService::createUserModel)
                .orElse(new UserModel("","",""));
    }

}
