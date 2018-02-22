package by.it.company.demoSpringHibernate.wcontrollers;

import by.it.company.demoSpringHibernate.exceptions.ServicesException;
import by.it.company.demoSpringHibernate.models.EmployeeModel;
import by.it.company.demoSpringHibernate.services.IFacadeServices;
import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/")
public class EmployeeController {

    private Logger logger = Logger.getLogger(EmployeeController.class);

    @Autowired
    IFacadeServices facadeServices;

    @RequestMapping(value = "/")
    public ResponseEntity helloPage(){

        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "employee", method = RequestMethod.GET)
    public ResponseEntity<List> getAllEmployees() throws Exception {
        try {
            List employees = facadeServices.getEmployeesList();
            return new ResponseEntity(employees, HttpStatus.OK);
        } catch (ServiceException e) {
            logger.debug("[EmployeeController/getAllEmployees]: " + e.getLocalizedMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "employee/{idEmployee}", method = RequestMethod.GET)
    public ResponseEntity getEmployee(@PathVariable("idEmployee") Long idEmployee) throws Exception {
        try {
            EmployeeModel employee = facadeServices.getEmployee(idEmployee);
//            if (!employee.isPresent()) return new ResponseEntity(HttpStatus.BAD_REQUEST);
            return new ResponseEntity(employee, HttpStatus.OK);
        } catch (ServiceException e) {
            logger.debug("[EmployeeController/getEmployee]: " + e.getLocalizedMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "employee", method = RequestMethod.POST)
    public ResponseEntity<EmployeeModel> addNewEmployee(@RequestBody EmployeeModel newEmployee) throws Exception {

        if (newEmployee == null) {
            logger.debug("[EmployeeController/addNewEmployee] 'null' employee in request!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            EmployeeModel employee = facadeServices.addNewEmployee(newEmployee);
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        } catch (ServiceException e) {
            logger.debug("[EmployeeController/addNewEmployee]: " + e.getLocalizedMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "employee/{idEmployee}", method = RequestMethod.PUT)
    public ResponseEntity updateEmployee(@PathVariable("idEmployee") Long idEmployee,
                                         @RequestBody EmployeeModel employeeModel) throws Exception {

        try {
            facadeServices.updateEmployee(idEmployee, employeeModel);
            return new ResponseEntity(HttpStatus.OK);

//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (ServiceException e) {
            logger.debug("[EmployeeController/updateEmployee]: " + e.getLocalizedMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "employee/{idEmployee}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(@PathVariable("idEmployee") Long idEmployee) throws Exception {
        try {
            facadeServices.deleteEmployee(idEmployee);
            return new ResponseEntity(HttpStatus.OK);
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (ServicesException e) {
            logger.debug("[EmployeeController/deleteEmployee]: " + e.getLocalizedMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @PreAuthorize("hasRole('USER')")
//    @RequestMapping(value = "employee/{surname}", method = RequestMethod.GET)
    public ResponseEntity getUsersBySurname(@PathVariable("surname") String surname) throws Exception {
        try {
            List employees = facadeServices.getEmployee(surname);
            return new ResponseEntity(employees, HttpStatus.OK);
        } catch (ServiceException e) {
            logger.debug("[EmployeeController/getUsersBySurname]: " + e.getLocalizedMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
