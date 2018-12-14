package spring.example.rest;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import spring.example.service.DepartmentService;
import spring.example.util.HeaderUtil;
import spring.example.util.PaginationUtil;
import spring.example.vm.DepartmentVM;

import java.util.List;

/**
 * Created on : 14.12.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */
@RestController
@RequestMapping(value = "/api")
public class DepartmentRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department/{id}")
    public ResponseEntity<DepartmentVM> getDepartmentById(@PathVariable("id") String id) {
        DepartmentVM departmentVM = departmentService.findById(id);
        if (departmentVM != null) {
            return new ResponseEntity<>(departmentVM, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/department")
    public ResponseEntity<List<DepartmentVM>> getAllDepartment() {
        List<DepartmentVM> list = departmentService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/department-p")
    public ResponseEntity<List<DepartmentVM>> getAllDepartmentP(@RequestParam int page, @RequestParam int size) {
        Page<DepartmentVM> pageDepartment = departmentService.getAllPage(PageRequest.of(page, size));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(pageDepartment, "/api/department-p");
        return new ResponseEntity<>(pageDepartment.getContent(), headers, HttpStatus.OK);
    }

    @PostMapping("/department")
    public ResponseEntity<Void> addDepartment(@RequestBody DepartmentVM departmentVM, UriComponentsBuilder builder) {
        DepartmentVM depVM = departmentService.save(departmentVM);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/department/{id}").buildAndExpand(depVM.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @PutMapping("department")
    public ResponseEntity<Void> updateDepartment(@RequestBody DepartmentVM departmentVM) {
        departmentVM = departmentService.save(departmentVM);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("department.updated", departmentVM.getName())).build();
    }

    @DeleteMapping("department/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id") String id) {
        departmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("department.deleted", String.valueOf(id))).build();
    }
}
