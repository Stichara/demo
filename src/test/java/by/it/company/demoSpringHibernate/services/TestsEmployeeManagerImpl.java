package by.it.company.demoSpringHibernate.services;

import by.it.company.demoSpringHibernate.dao.entities.Employee;
import by.it.company.demoSpringHibernate.dao.repositories.EmployeeRepository;
import by.it.company.demoSpringHibernate.models.EmployeeModel;
import by.it.company.demoSpringHibernate.services.managers.impl.EmployeeManagerImpl;
import by.it.company.demoSpringHibernate.services.managers.impl.UtilsServiceImpl;
import by.it.company.demoSpringHibernate.services.managers.interfaces.IUtilsService;
import org.junit.Before;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TestsEmployeeManagerImpl {

    @Mock
    private EmployeeRepository employeeRepository;

    @Spy
    private IUtilsService utilsService = new UtilsServiceImpl();

    @InjectMocks
    private EmployeeManagerImpl employeeManager;

    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addNewEmployeeTest() throws Exception {
        EmployeeModel employeeModel = new EmployeeModel(4L,"name","surname");
        Employee employee = new Employee(1L,"name","surname");
        EmployeeModel compareModel = new EmployeeModel(1L,"name","surname");

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        assertEquals(employeeManager.addNewEmployee(employeeModel),compareModel);

        verify(employeeRepository,times(1)).save(any(Employee.class));
        verify(utilsService, times(1)).createEmployeeModel(any(Employee.class));
    }

    @Test
    public void getEmployeeTest() throws Exception {
        EmployeeModel compareModel = new EmployeeModel(1L,"name","surname");
        Employee employee = new Employee(1L,"name", "surname");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
//        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());

//        EmployeeModel result = employeeManager.getEmployee(2L);
//        assertFalse(result.isPresent());
//        verify(employeeRepository,times(1)).findById(2L);
//        verify(utilsService, times(0)).createEmployeeModel(any(Employee.class));

//        result = employeeManager.getEmployee(1L);
        assertEquals(employeeManager.getEmployee(1L),compareModel);
        verify(employeeRepository,times(1)).findById(1L);
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

        when(employeeRepository.findAll()).thenReturn(employees);

        assertEquals(employeeManager.getEmployeesList(),employeeModels);
        verify(employeeRepository,times(1)).findAll();
        verify(utilsService, times(1)).createEmployeeModelList(employees);

    }

    @Test
    public void updateEmployeeTest() throws Exception {
        EmployeeModel employeeModel = new EmployeeModel(10L, "name7", "surname7");
        Employee employee = new Employee(7L, "name5","name5");

        when(employeeRepository.findById(7L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);
        employeeManager.updateEmployee(7L,employeeModel);
        verify(employeeRepository,times(1)).findById(7L);
        verify(employeeRepository,times(1)).save(employee);

//        when(employeeDao.get(10L)).thenReturn(null);
//        assertEquals(employeeManager.updateEmployee(10L,employeeModel), false);
//        verify(employeeDao,times(1)).get(10L);
//        verify(employeeDao,times(1)).update(employee);
    }

    @Test
    public void deleteEmployeeTest() throws Exception {

        Employee employee = new Employee(2L, "name2","name2");

//        when(employeeRepository.findById(1L)).thenReturn(null);
//        when(employeeRepository.findById(2L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).deleteById(2L);

//        assertEquals(employeeManager.deleteEmployee(1L), false);
//        verify(employeeDao, times(1)).get(1L);
//        verify(employeeDao, times(0)).delete(any(Employee.class));

        employeeManager.deleteEmployee(2L);
//        verify(employeeRepository, times(1)).findById(2L);
        verify(employeeRepository, times(1)).deleteById(2L);

    }

    @Test
    public void getEmployeeBySurnameTest() throws Exception{
        String surname = "surname";
        String surname2 = "surname2";

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L,"name1","surname"));
        employees.add(new Employee(2L,"name2", "surname"));
        employees.add(new Employee(3L,"name3", "surname"));

        List<EmployeeModel> employeeModels = new ArrayList<>();
        employeeModels.add(new EmployeeModel(1L,"name1","surname"));
        employeeModels.add(new EmployeeModel(2L,"name2", "surname"));
        employeeModels.add(new EmployeeModel(3L,"name3", "surname"));

        when(employeeRepository.findEmployeeBySurname(surname)).thenReturn(employees);
        when(employeeRepository.findEmployeeBySurname(surname2)).thenReturn(new ArrayList());

        assertEquals(employeeManager.getEmployee(surname),employeeModels);
        List result = employeeManager.getEmployee(surname2);
        assertEquals(result.size(),0);
        verify(employeeRepository,times(1)).findEmployeeBySurname(surname);
        verify(employeeRepository,times(1)).findEmployeeBySurname(surname2);
        verify(utilsService, times(2)).createEmployeeModelList(anyList());
    }
}
