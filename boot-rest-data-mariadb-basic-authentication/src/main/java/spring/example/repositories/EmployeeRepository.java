package spring.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import spring.example.jpa.entity.Employee;

import java.util.List;

/**
 * Created on : 30.06.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findEmployeesById(Long age);

    @Query("select emp from Employee emp where emp.age >= ?1 and emp.age <= ?2")
    List<Employee> findEmployeesBetweenAge(int from, int to);

    @Modifying
    @Transactional
    @Query("delete from Employee where id = :id")
    void deleteEmployeeById(@Param("id") Long id);
}
