package org.service.user.service;

import org.service.user.entity.projection.VwUserWithRole;
import org.service.user.repositories.UsersRepository;
import org.service.user.vm.UserWithRoleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope(value = "singleton")
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Transactional(readOnly = true)
    public UserWithRoleVM getUserWithRoleVM(UserWithRoleVM user) {
        VwUserWithRole vwUserWithRole = usersRepository.findActiveUserForLogin(
                user.getName(), user.getPass()
        );
        if (vwUserWithRole != null) {
            UserWithRoleVM userWithRoleVM = new UserWithRoleVM();
            userWithRoleVM.setUserID(vwUserWithRole.getUserID());
            userWithRoleVM.setName(vwUserWithRole.getName());
            userWithRoleVM.setPass(vwUserWithRole.getPass());
            userWithRoleVM.setActive(vwUserWithRole.getActive());
            userWithRoleVM.setRoleID(vwUserWithRole.getRoleID());
            userWithRoleVM.setAuthority(vwUserWithRole.getAuthority());

            return userWithRoleVM;
        }
        return null;
    }

}
