package spring.example.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import spring.example.jpa.entity.Employee;
import spring.example.vm.interfaces.VMInterface;

import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;

/**
 * Created on : 02.07.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */
public class EmployeeVM implements VMInterface {

    private Long id;

    @Size(max = 256)
    private String name;

    private Integer age;

    private Instant date;

    private Set<DepartmentVM> departmentsVM;

    public EmployeeVM() {
    }

    public EmployeeVM(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.age = employee.getAge();
        this.date = employee.getDate();
    }

    @JsonIgnore
    public Employee getEmployee() {
        Employee employee = new Employee();
        employee.setId(this.id);
        employee.setName(this.name);
        employee.setAge(this.age);
        employee.setDate(this.date);

        return employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Set<DepartmentVM> getDepartmentsVM() {
        return departmentsVM;
    }

    public void setDepartmentsVM(Set<DepartmentVM> departmentsVM) {
        this.departmentsVM = departmentsVM;
    }

    @Override
    public String toString() {
        return "EmployeeVM{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                ", departmentsVM=" + departmentsVM +
                '}';
    }
}
