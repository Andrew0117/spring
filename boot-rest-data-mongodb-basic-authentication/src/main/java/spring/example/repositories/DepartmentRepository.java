package spring.example.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import spring.example.jpa.entity.Department;

/**
 * Created on : 14.12.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */

@Repository
public interface DepartmentRepository extends MongoRepository<Department, ObjectId> {

}
