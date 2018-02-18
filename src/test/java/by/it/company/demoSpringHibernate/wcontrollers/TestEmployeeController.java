package by.it.company.demoSpringHibernate.wcontrollers;

import by.it.company.demoSpringHibernate.config.AppConfig;
import by.it.company.demoSpringHibernate.models.EmployeeModel;
import by.it.company.demoSpringHibernate.services.IFacadeServices;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration(classes = {AppConfig.class})
//@WebAppConfiguration
public class TestEmployeeController {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

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



        when(facadeServices.getEmployeesList()).thenReturn(employees);
        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk());

//        when(facadeServices.getEmployee(1L)).thenReturn(Optional.empty());
//        mockMvc.perform(get("/employee/1"))
//                .andExpect(status().isBadRequest());
    }
}
