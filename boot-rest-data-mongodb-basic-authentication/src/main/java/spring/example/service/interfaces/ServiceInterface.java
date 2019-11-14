package spring.example.service.interfaces;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import spring.example.vm.interfaces.VMInterface;

import java.util.List;

/**
 * Created on : 14.12.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */
public interface ServiceInterface<T extends VMInterface> {

    @Transactional(readOnly = true)
    List<T> getAll();

    @Transactional(readOnly = true)
    Page<T> getAllPage(Pageable pageable);

    @Transactional(readOnly = true)
    <S extends T> S findById(String id);

    @Transactional(isolation = Isolation.READ_COMMITTED)
    <S extends T> S save(S s);

    @Transactional(isolation = Isolation.READ_COMMITTED)
    void delete(String id);
}
