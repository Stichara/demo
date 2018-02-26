package by.it.company.demoSpringHibernate.wcontrollers;


import by.it.company.demoSpringHibernate.models.EmployeeModel;
import by.it.company.demoSpringHibernate.services.IFacadeServices;
import by.it.company.demoSpringHibernate.utils.TestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TestEmployeeController {

    private MockMvc mockMvc;

    @Mock
    private IFacadeServices facadeServices;

    @InjectMocks
    private EmployeeController employeeController = new EmployeeController();

    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .build();
    }

    @After
    public void tearDown() throws Exception{

    }

    @Test
    public void getAllEmployeesTest() throws Exception {

        List<EmployeeModel> employees = new ArrayList<>();
        employees.add(new EmployeeModel(1l,"name1","surname1"));
        employees.add(new EmployeeModel(2l,"name2","surname2"));
        employees.add(new EmployeeModel(3l, "name3", "surname3"));

//        when(facadeServices.getEmployeesList(1,3)).thenReturn(employees);
//        mockMvc.perform(get("/employee"))
////                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk());

//        verify(facadeServices,times(1)).getEmployeesList(1,3);

    }

    @Test
    public void getEmployeeTest() throws Exception {
        EmployeeModel employee = new EmployeeModel(1L,"name","surname" );

        when(facadeServices.getEmployee(1L)).thenReturn(employee);
//        when(facadeServices.getEmployee(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/employee/{idEmployee}",1))
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

//        mockMvc.perform(get("/employee/{idEmployee}",2))
//                .andExpect(status().isBadRequest());

        verify(facadeServices, times(1)).getEmployee(1l);
//        verify(facadeServices, times(1)).getEmployee(2l);

    }

    @Test
    public void addNewEmployeeTest() throws Exception {

    }

    @Test
    public void updateEmployeeTest() throws Exception {

    }

    @Test
    public void deleteEmployeeTest() throws Exception {
        doNothing().when(facadeServices).deleteEmployee(1L);

        mockMvc.perform(delete("/employee/{idEmployee}",1L))
                .andExpect(status().isOk());
        verify(facadeServices, times(1)).deleteEmployee(1l);
    }
}
