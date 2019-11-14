package spring.example.jpa.entity;

import spring.example.jpa.entity.embeddable.DepartmentEmployeeId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * Created on : 02.07.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */
@Entity
@Table(name = "department_employee", catalog = "db")
@AssociationOverrides({
        @AssociationOverride(name = "pk.department",
                joinColumns = @JoinColumn(name = "fkDepartment")),
        @AssociationOverride(name = "pk.employee",
                joinColumns = @JoinColumn(name = "fkEmployee"))})
public class DepartmentEmployee implements Serializable {

    private static final long serialVersionUID = -5044656295337683817L;

    @EmbeddedId
    private DepartmentEmployeeId pk = new DepartmentEmployeeId();

    @Column(name = "cDATE", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant date;

    public DepartmentEmployee() {
    }

    public DepartmentEmployeeId getPk() {
        return pk;
    }

    public void setPk(DepartmentEmployeeId pk) {
        this.pk = pk;
    }

    @Transient
    public Department getDepartment() {
        return getPk().getDepartment();
    }

    public void setDepartment(Department department) {
        getPk().setDepartment(department);
    }

    @Transient
    public Employee getEmployee() {
        return getPk().getEmployee();
    }

    public void setEmployee(Employee employee) {
        getPk().setEmployee(employee);
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentEmployee that = (DepartmentEmployee) o;

        if (pk != null ? !pk.equals(that.pk) : that.pk != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return (pk != null ? pk.hashCode() : 0);
    }

}
