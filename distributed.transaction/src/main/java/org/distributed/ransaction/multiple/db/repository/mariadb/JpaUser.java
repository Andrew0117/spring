package org.distributed.ransaction.multiple.db.repository.mariadb;

import org.distributed.ransaction.multiple.db.entities.mariadb.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Berezhnov Andrey on 2024-04-03 20:45
 * @see <a href="#">Andrey at andrew.my@yahoo.com</a>
 */
public interface JpaUser extends JpaRepository<User, Long> {
}
