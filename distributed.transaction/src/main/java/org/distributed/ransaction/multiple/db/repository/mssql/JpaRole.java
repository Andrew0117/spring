package org.distributed.ransaction.multiple.db.repository.mssql;

import org.distributed.ransaction.multiple.db.entities.mssql.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Berezhnov Andrey on 2024-04-03 20:46
 * @see <a href="#">Andrey at andrew.my@yahoo.com</a>
 */
public interface JpaRole extends JpaRepository<Role, Long> {
}
