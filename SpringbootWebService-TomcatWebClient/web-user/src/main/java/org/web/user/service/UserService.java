package org.web.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.web.user.vm.UserWithRoleVM;

@Service
@Scope(value = "singleton")
public class UserService {

    @Autowired
    private AppServiceI appService;

    public UserWithRoleVM user(UserWithRoleVM user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserWithRoleVM> userWithRoleVMHttpEntity = new HttpEntity<>(user, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<UserWithRoleVM> responseEntity = restTemplate.exchange(
                appService.getHost() + "/user/",
                HttpMethod.POST, userWithRoleVMHttpEntity,
                UserWithRoleVM.class);

        return responseEntity.getBody();
    }

}
