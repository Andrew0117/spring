package org.distributed.ransaction.multiple.db.services;

import jakarta.persistence.RollbackException;
import org.distributed.ransaction.multiple.db.entities.mariadb.User;
import org.distributed.ransaction.multiple.db.entities.mssql.Role;
import org.distributed.ransaction.multiple.db.repository.mariadb.JpaUser;
import org.distributed.ransaction.multiple.db.repository.mssql.JpaRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Berezhnov Andrey on 2024-04-03 20:47
 * @see <a href="#">Andrey at andrew.my@yahoo.com</a>
 */
@Service
public class DistributedTransaction {

    private JpaUser jpaUser;

    private JpaRole jpaRole;

    public DistributedTransaction(JpaUser jpaUser, JpaRole jpaRole) {
        this.jpaUser = jpaUser;
        this.jpaRole = jpaRole;
    }

    @Transactional(value = "chainedTransactionManager", propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public void insertIntoMultipleDBs() {
        var user = new User();
        user.setName("Patrik");
        user = jpaUser.save(user);

        var role = new Role();
        role.setName("user");
        role = jpaRole.save(role);

        System.out.println(user);
        System.out.println(role);
    }

    @Transactional(value = "chainedTransactionManager", propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public void rollbackIntoMultipleDBs() throws RollbackException {
        var user = new User();
        user.setName("Patrik");
        user = jpaUser.save(user);

        var role = new Role();
        role.setName("user");
        role = jpaRole.save(role);

        System.out.println(user);
        System.out.println(role);

        throw new RollbackException("rollback all databases");
    }

}
