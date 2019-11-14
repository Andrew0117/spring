package spring.example.jpa.entity.embeddable;

import spring.example.jpa.entity.Department;
import spring.example.jpa.entity.Employee;

import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created on : 02.07.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */
public class DepartmentEmployeeId implements Serializable {

    private static final long serialVersionUID = -617896424017745768L;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Employee employee;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentEmployeeId that = (DepartmentEmployeeId) o;

        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        return employee != null ? employee.equals(that.employee) : that.employee == null;
    }

    @Override
    public int hashCode() {
        int result = department != null ? department.hashCode() : 0;
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        return result;
    }

}
