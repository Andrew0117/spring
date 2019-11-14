package spring.example.service.interfaces;

import org.springframework.transaction.annotation.Transactional;
import spring.example.vm.interfaces.VMInterface;

import java.util.List;

/**
 * Created on : 06.07.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */
public interface EmployeeInterface<T extends VMInterface> extends ServiceInterface<T> {

    @Transactional(readOnly = true)
    List<T> getAllAge(int from, int to);
}
