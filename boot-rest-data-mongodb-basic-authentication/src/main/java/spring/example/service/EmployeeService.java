package spring.example.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.example.jpa.entity.Department;
import spring.example.jpa.entity.Employee;
import spring.example.repositories.DepartmentRepository;
import spring.example.repositories.EmployeeRepository;
import spring.example.service.interfaces.EmployeeInterface;
import spring.example.vm.DepartmentVM;
import spring.example.vm.EmployeeVM;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on : 14.12.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */
@Service(value = "employeeService")
@Scope(value = "singleton")
public class EmployeeService implements EmployeeInterface<EmployeeVM> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<EmployeeVM> getAll() {
        List<Employee> listEmployee = employeeRepository.findAll();
        List<EmployeeVM> listEmployeeVM = listEmployee.stream().map(EmployeeVM::new).collect(Collectors.toList());
        for (EmployeeVM empVM : listEmployeeVM) {
            for (Employee emp : listEmployee) {
                if (empVM.getId().equals(emp.getId())) {
                    empVM.setDepartmentsVM(emp.getDepartments().stream().map(DepartmentVM::new).collect(Collectors.toSet()));
                }
            }
        }
        return listEmployeeVM;
    }

    @Override
    public Page<EmployeeVM> getAllPage(Pageable pageable) {
        List<Employee> listEmployee = employeeRepository.findAll(pageable).getContent();
        List<EmployeeVM> listEmployeeVM = listEmployee.stream().map(EmployeeVM::new).collect(Collectors.toList());
        for (EmployeeVM empVM : listEmployeeVM) {
            for (Employee emp : listEmployee) {
                if (empVM.getId().equals(emp.getId())) {
                    empVM.setDepartmentsVM(emp.getDepartments().stream().map(DepartmentVM::new).collect(Collectors.toSet()));
                }
            }
        }
        Page<EmployeeVM> pageEmployeeVM = new PageImpl<EmployeeVM>(listEmployeeVM, pageable, listEmployeeVM.size());

        return pageEmployeeVM;
    }

    @Override
    public EmployeeVM findById(String id) {
        Employee employee = employeeRepository.findEmployeesById(new ObjectId(id));
        EmployeeVM employeeVM = new EmployeeVM(employee);
        employeeVM.setDepartmentsVM(employee.getDepartments().stream().map(DepartmentVM::new).collect(Collectors.toSet()));

        return employeeVM;
    }

    @Override
    public List<EmployeeVM> getAllAge(int from, int to) {
        List<Employee> listEmployee = employeeRepository.findEmployeesBetweenAge(from, to);
        List<EmployeeVM> listEmployeeVM = listEmployee.stream().map(EmployeeVM::new).collect(Collectors.toList());
        for (EmployeeVM empVM : listEmployeeVM) {
            for (Employee emp : listEmployee) {
                if (empVM.getId().equals(emp.getId())) {
                    empVM.setDepartmentsVM(emp.getDepartments().stream().map(DepartmentVM::new).collect(Collectors.toSet()));
                }
            }
        }
        return listEmployeeVM;
    }

    @Override
    public EmployeeVM save(EmployeeVM employeeVM) {
        EmployeeVM empVM = new EmployeeVM(employeeRepository.save(employeeVM.getEmployee()));
        Set<DepartmentVM> departmentVMS = new HashSet<>();
        if (employeeVM.getDepartmentsVM() != null && !employeeVM.getDepartmentsVM().isEmpty()) {
            employeeVM.getDepartmentsVM().forEach(departmentVM -> {
                Department department = departmentVM.getDepartment();
                department.setEmployees(Arrays.asList(new Employee[]{empVM.getEmployee()}));
                departmentVMS.add(new DepartmentVM(departmentRepository.save(department)));
            });
        }
        empVM.setDepartmentsVM(departmentVMS);
        return empVM;
    }

    @Override
    public void delete(String id) {
        employeeRepository.deleteById(new ObjectId(id));
    }

}
