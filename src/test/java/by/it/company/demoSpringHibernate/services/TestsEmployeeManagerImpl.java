package by.it.company.demoSpringHibernate.services;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import by.it.company.demoSpringHibernate.dao.interfaces.IEmployeeDao;
import by.it.company.demoSpringHibernate.exceptions.DaoException;
import by.it.company.demoSpringHibernate.exceptions.ServicesException;
import by.it.company.demoSpringHibernate.models.EmployeeModel;
import by.it.company.demoSpringHibernate.services.managers.impl.EmployeeManagerImpl;
import by.it.company.demoSpringHibernate.services.managers.impl.UtilsServiceImpl;
import by.it.company.demoSpringHibernate.services.managers.interfaces.IUtilsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TestsEmployeeManagerImpl {

    @Mock
    private IEmployeeDao employeeDao;

    @Spy
    private IUtilsService utilsService = new UtilsServiceImpl();

    @InjectMocks
    private EmployeeManagerImpl employeeManager;

    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Ignore
    @Test
    public void addNewEmployeeTest() throws Exception {
        EmployeeModel employeeModel = new EmployeeModel(4L,"name","surname");
        EmployeeModel compareModel = new EmployeeModel(null,"name","surname");

        when(employeeDao.add(any(Employee.class))).thenReturn(1L);

        assertEquals(employeeManager.addNewEmployee(employeeModel),compareModel);

        verify(employeeDao,times(1)).add(any(Employee.class));
        verify(utilsService, times(1)).createEmployeeModel(any(Employee.class));
    }

    @Test
    public void getEmployeTest() throws Exception {
        EmployeeModel compareModel = new EmployeeModel(1L,"name","surname");
        Employee employee = new Employee(1L,"name", "surname");

        when(employeeDao.get(1L)).thenReturn(employee);
        when(employeeDao.get(2L)).thenReturn(null);

        Optional result = employeeManager.getEmployee(2L);
        assertFalse(result.isPresent());
        verify(employeeDao,times(1)).get(2L);
        verify(utilsService, times(0)).createEmployeeModel(any(Employee.class));

        result = employeeManager.getEmployee(1L);
        assertEquals(result.get(),compareModel);
        verify(employeeDao,times(1)).get(1L);
        verify(utilsService, times(1)).createEmployeeModel(employee);


    }

    @Test
    public void getEmployeesListTest() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L,"name1","surname1"));
        employees.add(new Employee(2l,"name2", "surname2"));
        employees.add(new Employee(3L,"name3", "surname3"));

        List<EmployeeModel> employeeModels = new ArrayList<>();
        employeeModels.add(new EmployeeModel(1L,"name1","surname1"));
        employeeModels.add(new EmployeeModel(2l,"name2", "surname2"));
        employeeModels.add(new EmployeeModel(3L,"name3", "surname3"));

        when(employeeDao.getAll()).thenReturn(employees);

        assertEquals(employeeManager.getEmployeesList(),employeeModels);
        verify(employeeDao,times(1)).getAll();
        verify(utilsService, times(1)).createEmployeeModelList(employees);

    }

    @Test
    public void updateEmployeeTest() throws Exception {
        EmployeeModel employeeModel = new EmployeeModel(10L, "name7", "surname7");
        Employee employee = new Employee(7L, "name5","name5");

        when(employeeDao.get(7L)).thenReturn(employee);
        doNothing().when(employeeDao).update(employee);
        assertEquals(employeeManager.updateEmployee(7L,employeeModel),true);
        verify(employeeDao,times(1)).get(7L);
        verify(employeeDao,times(1)).update(employee);

        when(employeeDao.get(10L)).thenReturn(null);
        assertEquals(employeeManager.updateEmployee(10L,employeeModel), false);
        verify(employeeDao,times(1)).get(10L);
        verify(employeeDao,times(1)).update(employee);
    }

    @Test
    public void deleteEmployeeTest() throws Exception {

    }
}
