package spring.example.rest;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import spring.example.service.EmployeeService;
import spring.example.util.HeaderUtil;
import spring.example.util.PaginationUtil;
import spring.example.vm.EmployeeVM;

import java.util.List;

/**
 * Created on : 14.12.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */

@RestController
@RequestMapping(value = "/api")
public class EmployeeRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier(value = "employeeService")
    private EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeVM> getEmployeeById(@PathVariable("id") String id) {
        EmployeeVM employeeVM = employeeService.findById(id);
        if (employeeVM != null) {
            return new ResponseEntity<>(employeeVM, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeVM>> getAllEmployee() {
        List<EmployeeVM> list = employeeService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/employee-p")
    public ResponseEntity<List<EmployeeVM>> getAllEmployeeP(@RequestParam int page, @RequestParam int size) {
        Page<EmployeeVM> pageEmployee = employeeService.getAllPage(PageRequest.of(page, size));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(pageEmployee, "/api/employee-p");
        return new ResponseEntity<>(pageEmployee.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/employee-age")
    public ResponseEntity<List<EmployeeVM>> getAllEmployeeAge(@RequestParam int from, @RequestParam int to) {
        List<EmployeeVM> list = employeeService.getAllAge(from, to);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity<Void> addEmployee(@RequestBody EmployeeVM employeeVM, UriComponentsBuilder builder) {
        employeeVM = employeeService.save(employeeVM);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/employee/{id}").buildAndExpand(employeeVM.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("employee")
    public ResponseEntity<Void> updateEmployee(@RequestBody EmployeeVM employeeVM) {
        employeeVM = employeeService.save(employeeVM);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("employee.updated", employeeVM.getName())).build();
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") String id) {
        employeeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("employee.deleted", String.valueOf(id))).build();
    }

}
