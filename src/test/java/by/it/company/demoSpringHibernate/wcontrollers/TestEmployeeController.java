package by.it.company.demoSpringHibernate.wcontrollers;


import by.it.company.demoSpringHibernate.models.EmployeeModel;
import by.it.company.demoSpringHibernate.services.FacadeServices;
import by.it.company.demoSpringHibernate.utils.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TestEmployeeController {

    private MockMvc mockMvc;

    @Mock
    private FacadeServices facadeServices;

    @InjectMocks
    private EmployeeController employeeController = new EmployeeController();

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .build();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getAllEmployeesTest() throws Exception {

        List<EmployeeModel> employees = new ArrayList<>();
        employees.add(new EmployeeModel(1l, "name1", "surname1"));
        employees.add(new EmployeeModel(2l, "name2", "surname2"));
        employees.add(new EmployeeModel(3l, "name3", "surname3"));
        ObjectMapper mapper = new ObjectMapper();

        when(facadeServices.getEmployeesList(1, 3)).thenReturn(employees);
        MvcResult result = mockMvc.perform(get("/employee")
                .param("page", String.valueOf(1))
                .param("sizePage", String.valueOf(3)))
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(mapper.writeValueAsString(employees),result.getResponse().getContentAsString());
        verify(facadeServices, times(1)).getEmployeesList(1, 3);

    }

    @Test
    public void getEmployeeTest() throws Exception {
        EmployeeModel employee = new EmployeeModel(1L, "name", "surname");


        when(facadeServices.getEmployee(1L)).thenReturn(employee);

        mockMvc.perform(get("/employee/{idEmployee}", 1))
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        verify(facadeServices, times(1)).getEmployee(1l);

    }

    @Test
    public void addNewEmployeeTest() throws Exception {
        EmployeeModel employeeModel = new EmployeeModel(4l, "firstName", "surname");
        EmployeeModel employeeModelCompare = new EmployeeModel(1l, "firstName", "surname");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(employeeModel);

        when(facadeServices.addNewEmployee(employeeModel)).thenReturn(employeeModelCompare);

        MvcResult result = mockMvc.perform(post("/employee/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8).content(json))
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andReturn();
        assertEquals(mapper.writeValueAsString(employeeModelCompare), result.getResponse().getContentAsString());
        verify(facadeServices, times(1)).addNewEmployee(any(EmployeeModel.class));

    }

    @Test
    public void updateEmployeeTest() throws Exception {
        EmployeeModel employeeModel = new EmployeeModel(2l, "firstName2", "surname2");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(employeeModel);

        doNothing().when(facadeServices).updateEmployee(employeeModel.getId(), employeeModel); //any(EmployeeModel.class));
        mockMvc.perform(put("/employee/{idEmployee}", employeeModel.getId())
                .contentType(TestUtil.APPLICATION_JSON_UTF8).content(json))
                .andExpect(status().isOk());
        verify(facadeServices, times(1)).updateEmployee(any(Long.class), any(EmployeeModel.class));
    }

    @Test
    public void deleteEmployeeTest() throws Exception {
        doNothing().when(facadeServices).deleteEmployee(1L);

        mockMvc.perform(delete("/employee/{idEmployee}", 1L))
                .andExpect(status().isOk());
        verify(facadeServices, times(1)).deleteEmployee(1l);
    }
}
