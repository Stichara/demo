package by.it.company.demoSpringHibernate.wcontrollers;

import by.it.company.demoSpringHibernate.models.EmployeeModel;
import by.it.company.demoSpringHibernate.services.FacadeServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class EmployeeController {

    private Logger logger = Logger.getLogger(EmployeeController.class);

    @Autowired
    FacadeServices facadeServices;

    @RequestMapping(value = "/")
    public ResponseEntity helloPage() {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "employee", method = RequestMethod.GET)
    public ResponseEntity<List> getEmployeesWithPagination(@RequestParam Integer page,
                                                           @RequestParam Integer sizePage) throws Exception {
        List employees = facadeServices.getEmployeesList(page,sizePage);
        return new ResponseEntity(employees, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "employee/{idEmployee}", method = RequestMethod.GET)
    public ResponseEntity getEmployee(@PathVariable("idEmployee") Long idEmployee) throws Exception {

        EmployeeModel employee = facadeServices.getEmployee(idEmployee);
        return new ResponseEntity(employee, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "employee", method = RequestMethod.POST)
    public ResponseEntity<EmployeeModel> addNewEmployee(@RequestBody EmployeeModel newEmployee) throws Exception {

        EmployeeModel employee = facadeServices.addNewEmployee(newEmployee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "employee/{idEmployee}", method = RequestMethod.PUT)
    public ResponseEntity updateEmployee(@PathVariable("idEmployee") Long idEmployee,
                                         @RequestBody EmployeeModel employeeModel) throws Exception {

        facadeServices.updateEmployee(idEmployee, employeeModel);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "employee/{idEmployee}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(@PathVariable("idEmployee") Long idEmployee) throws Exception {

        facadeServices.deleteEmployee(idEmployee);
        return new ResponseEntity(HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('USER')")
//    @RequestMapping(value = "employee/{surname}", method = RequestMethod.GET)
    public ResponseEntity getUsersBySurname(@PathVariable("surname") String surname) throws Exception {
        List employees = facadeServices.getEmployee(surname);
        return new ResponseEntity(employees, HttpStatus.OK);
    }


}
