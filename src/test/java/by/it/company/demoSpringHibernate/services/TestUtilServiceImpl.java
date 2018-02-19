package by.it.company.demoSpringHibernate.services;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import by.it.company.demoSpringHibernate.dao.entities.Role;
import by.it.company.demoSpringHibernate.dao.entities.User;
import by.it.company.demoSpringHibernate.models.EmployeeModel;
import by.it.company.demoSpringHibernate.models.UserModel;
import by.it.company.demoSpringHibernate.services.managers.impl.UtilsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class TestUtilServiceImpl {

    @InjectMocks
    private UtilsServiceImpl utilsService;

    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createEmployeeModelTest1() throws Exception {

        Employee employee = new Employee(1L,"name","surname");
        EmployeeModel compareModel = new EmployeeModel(1L,"name","surname");

        assertEquals( utilsService.createEmployeeModel(employee), compareModel);

    }

    @Test
    public void creaгteEmployeeModelListTest() throws Exception {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L,"name1","surname1"));
        employees.add(new Employee(2l,"name2", "surname2"));
        employees.add(new Employee(3L,"name3", "surname3"));

        List<EmployeeModel> employeeModels = new ArrayList<>();
        employeeModels.add(new EmployeeModel(1L,"name1","surname1"));
        employeeModels.add(new EmployeeModel(2l,"name2", "surname2"));
        employeeModels.add(new EmployeeModel(3L,"name3", "surname3"));

        assertEquals(utilsService.createEmployeeModelList(employees),employeeModels);
    }

    @Test
    public void createUserModelTest() throws Exception{
        User user = new User("user","pswd", new Role("ADMIN"));
        UserModel userModel = new UserModel("user","pswd","ADMIN");

        assertEquals(utilsService.createUserModel(user),userModel);

    }
}
