package spring.example.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import spring.example.vm.interfaces.VMInterface;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on : 30.06.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */

@Entity
@Table(name = "employee", catalog = "db")
public class Employee implements Serializable {

    private static final long serialVersionUID = -1184658370946198436L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 256, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(name = "cDATE", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant date;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "employees")
    private Set<Department> departments = new HashSet<>();

    public Employee() {
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

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!id.equals(employee.id)) return false;
        if (!name.equals(employee.name)) return false;
        return age.equals(employee.age);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + age.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}
