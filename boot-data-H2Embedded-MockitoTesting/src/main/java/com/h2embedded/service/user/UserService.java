package com.h2embedded.service.user;

import com.h2embedded.entity.User;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    @Transactional(isolation = Isolation.READ_COMMITTED)
    User save(Long orgId, User user);

    @Transactional(isolation = Isolation.READ_COMMITTED)
    User updateUser(Long orgId, Long userId, User user);

    @Transactional(readOnly = true)
    User findUser(Long orgId, Long userId);

    @Transactional(isolation = Isolation.READ_COMMITTED)
    void delete(Long orgId, Long userId);

    @Transactional(readOnly = true)
    List<User> getAllByOrganization(Long orgId);

}
