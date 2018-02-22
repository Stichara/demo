package by.it.company.demoSpringHibernate.services;

import by.it.company.demoSpringHibernate.dao.entities.Role;
import by.it.company.demoSpringHibernate.dao.entities.User;
import by.it.company.demoSpringHibernate.dao.interfaces.IUserDao;
import by.it.company.demoSpringHibernate.dao.repositories.UserRepository;
import by.it.company.demoSpringHibernate.models.UserModel;
import by.it.company.demoSpringHibernate.services.managers.impl.UserManagerImpl;
import by.it.company.demoSpringHibernate.services.managers.impl.UtilsServiceImpl;
import by.it.company.demoSpringHibernate.services.managers.interfaces.IUtilsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestUserManagerImpl {

    @Mock
    private UserRepository userRepository;

    @Spy
    private IUtilsService utilsService = new UtilsServiceImpl();

    @InjectMocks
    private UserManagerImpl userManager;

    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserTest() throws Exception{
        String login = "user";
        User user = new User("user","pswd", new Role("ADMIN"));
        UserModel userModel = new UserModel("user","pswd","ADMIN");

        when(userRepository.findById(login)).thenReturn(Optional.of(user));

        assertEquals(userManager.getUser(login), userModel);
        verify(userRepository, times(1)).findById(login);
        verify(utilsService, times(1)).createUserModel(user);
    }

}
