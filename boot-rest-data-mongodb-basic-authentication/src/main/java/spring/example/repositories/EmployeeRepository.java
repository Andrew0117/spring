package spring.example.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import spring.example.jpa.entity.Employee;

import java.util.List;

/**
 * Created on : 14.12.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {

    Employee findEmployeesById(ObjectId id);

    @Query("{ 'age' : { $gt: ?0, $lt: ?1 } }")
    List<Employee> findEmployeesBetweenAge(int from, int to);

}
