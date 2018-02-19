package by.it.company.demoSpringHibernate.services.managers.impl;

import by.it.company.demoSpringHibernate.dao.entities.User;
import by.it.company.demoSpringHibernate.dao.interfaces.IUserDao;
import by.it.company.demoSpringHibernate.exceptions.DaoException;
import by.it.company.demoSpringHibernate.exceptions.ServicesException;
import by.it.company.demoSpringHibernate.models.UserModel;
import by.it.company.demoSpringHibernate.services.managers.interfaces.IUserManager;
import by.it.company.demoSpringHibernate.services.managers.interfaces.IUtilsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserManagerImpl implements IUserManager {

    private Logger logger = Logger.getLogger(UserManagerImpl.class);

    @Autowired
    private IUserDao userDao;

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
        try {
            User user = userDao.getUserByLogin(login);
            return utilsService.createUserModel(user);
        }catch (DaoException e){
            logger.error("[UserManagerImpl/getUser] can not get user by login : "+login+": " +e.getLocalizedMessage() );
        }catch (ServicesException e){
            logger.error("[UserManagerImpl/getUser] can not create user model: " +e.getLocalizedMessage() );
        }

        return new UserModel("","","");
    }

}
