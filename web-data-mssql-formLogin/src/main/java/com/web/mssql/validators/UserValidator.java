package com.web.mssql.validators;

import com.web.mssql.filters.UserFilter;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Andrew <Andrey at andrew.my@yahoo.com> on 17.12.2018 13:53
 */
@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserFilter.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof UserFilter) {
            UserFilter userFilter = (UserFilter) target;
            if (userFilter.getName() == null || userFilter.getName().equals("")) {
                errors.rejectValue("name", "labelErrorName");
            }
            if (userFilter.getPass() == null || userFilter.getPass().equals("")) {
                errors.rejectValue("pass", "labelErrorPass");
            }
            if (userFilter.getActive() == null ||
                    (userFilter.getActive() < 0 || userFilter.getActive() > 1)
            ) {
                errors.rejectValue("active", "labelErrorActive");
            }
            if (userFilter.getRole() == null || userFilter.getRole() < 1) {
                errors.rejectValue("role", "labelErrorRole");
            }
        }
    }
}
