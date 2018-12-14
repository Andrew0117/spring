package spring.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import spring.example.jpa.entity.Department;

/**
 * Created on : 02.07.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Modifying
    @Transactional
    @Query("delete from Department where id = :id")
    void deleteDepartmentById(@Param("id") Long id);

}
