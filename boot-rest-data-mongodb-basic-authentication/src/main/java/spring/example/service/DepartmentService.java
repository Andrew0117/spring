package spring.example.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.example.jpa.entity.Department;
import spring.example.repositories.DepartmentRepository;
import spring.example.service.interfaces.ServiceInterface;
import spring.example.vm.DepartmentVM;
import spring.example.vm.EmployeeVM;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on : 14.12.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */
@Service(value = "departmentService")
@Scope(value = "singleton")
public class DepartmentService implements ServiceInterface<DepartmentVM> {

	
	 @Autowired
	 /* 
	 * @Qualifier(value = "departmentRepository")
	 */
    private DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentVM> getAll() {
        List<Department> listDepartment = departmentRepository.findAll();
        List<DepartmentVM> listDepartmentVM = listDepartment.stream().map(DepartmentVM::new).collect(Collectors.toList());
        for(DepartmentVM depVM : listDepartmentVM){
            for(Department dep : listDepartment){
                if(depVM.getId().equals(dep.getId())){
                    depVM.setEmployeesVM(dep.getEmployees().stream().map(EmployeeVM::new).collect(Collectors.toList()));
                }
            }
        }
        return listDepartmentVM;
    }

    @Override
    public Page<DepartmentVM> getAllPage(Pageable pageable) {
        List<Department> listDepartment = departmentRepository.findAll(pageable).getContent();
        List<DepartmentVM> listDepartmentVM = listDepartment.stream().map(DepartmentVM::new).collect(Collectors.toList());
        for(DepartmentVM depVM : listDepartmentVM){
            for(Department dep : listDepartment){
                if(depVM.getId().equals(dep.getId())){
                    depVM.setEmployeesVM(dep.getEmployees().stream().map(EmployeeVM::new).collect(Collectors.toList()));
                }
            }
        }
        Page<DepartmentVM> pageDepartmentVM = new PageImpl<>(listDepartmentVM, pageable, listDepartmentVM.size());

        return  pageDepartmentVM;
    }

    @Override
    public DepartmentVM findById(String id) {
        Department department = departmentRepository.findById(new ObjectId(id)).get();
        DepartmentVM departmentVM = new DepartmentVM(department);
        departmentVM.setEmployeesVM(department.getEmployees().stream().map(EmployeeVM::new).collect(Collectors.toList()));

        return departmentVM;
    }

    @Override
    public DepartmentVM save(DepartmentVM departmentVM) {
        Department department = departmentVM.getDepartment();
        department.setEmployees(
                departmentVM.getEmployeesVM().stream()
                        .map(EmployeeVM::getEmployee)
                        .collect(Collectors.toList())
        );
        department = departmentRepository.save(department);
        departmentVM = new DepartmentVM(department);
        List<EmployeeVM> lEmployeeVM = new LinkedList<>();
        departmentVM.setEmployeesVM(department.getEmployees().stream().map(EmployeeVM::new).collect(Collectors.toList()));

        return departmentVM;
    }

    @Override
    public void delete(String id) { departmentRepository.deleteById(new ObjectId(id)); }
}
