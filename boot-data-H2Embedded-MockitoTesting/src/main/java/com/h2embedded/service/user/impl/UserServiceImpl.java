package com.h2embedded.service.user.impl;

import com.h2embedded.jpa.repositories.UserRepository;
import com.h2embedded.entity.Organization;
import com.h2embedded.entity.User;
import com.h2embedded.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(Long orgId, User user) {
        Organization organization = new Organization();
        organization.setId(orgId);

        user.setOrganization(organization);
        user = userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(Long orgId, Long userId, User user) {
        userRepository.updateUser(user.getUsername(), user.getPassword(), orgId, userId);
        User newUser = new User();
        newUser.setId(userId);
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        return newUser;
    }

    @Override
    public User findUser(Long orgId, Long userId) {
        User user = userRepository.findUser(orgId, userId);
        return user;
    }

    @Override
    public void delete(Long orgId, Long userId) {
        userRepository.deleteUserByIds(orgId, userId);
    }

    @Override
    public List<User> getAllByOrganization(Long orgId) {
        List<User> lUser = userRepository.getAllByOrganization(orgId);
        return lUser;
    }
}
