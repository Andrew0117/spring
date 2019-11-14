package spring.example.jpa.entity;

import spring.example.jpa.entity.enumaration.DepartmentEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on : 02.07.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */
@Entity
@Table(name = "department", catalog = "db")
public class Department implements Serializable {

    private static final long serialVersionUID = -5378594792485940660L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 256, nullable = false)
    private String name;

    // TODO sample enum
    @Column(name = "dep")
    @Enumerated(EnumType.ORDINAL)
    private DepartmentEnum departmentEnum;

    @Column(name = "cDATE", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant date;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "department_employee",
            joinColumns = {@JoinColumn(name = "fkDepartment")},
            inverseJoinColumns = {@JoinColumn(name = "fkEmployee")})
    private List<Employee> employees = new ArrayList<>();

    public Department() {
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

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public DepartmentEnum getDepartmentEnum() {
        return departmentEnum;
    }

    public void setDepartmentEnum(DepartmentEnum departmentEnum) {
        this.departmentEnum = departmentEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return departmentEnum == that.departmentEnum;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (departmentEnum != null ? departmentEnum.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departmentEnum=" + departmentEnum +
                ", date=" + date +
                '}';
    }
}
