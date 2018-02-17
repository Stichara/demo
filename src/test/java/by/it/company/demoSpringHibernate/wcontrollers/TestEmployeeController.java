package by.it.company.demoSpringHibernate.wcontrollers;

import by.it.company.demoSpringHibernate.services.IFacadeServices;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(MockitoJUnitRunner.class)
public class TestEmployeeController {

    private MockMvc mockMvc;

    @Mock
    private IFacadeServices facadeServices;

    @InjectMocks
    private EmployeeController employeeController = new EmployeeController();

    @Before
    public void init() throws Exception{
//        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .build();
    }

    @After
    public void tearDown() throws Exception{

    }

    @Test
    public void getEmployeeTest() throws Exception {

        when(facadeServices.getEmployee(1L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/employee/1"))
                .andExpect(status().isBadRequest());
    }
}
