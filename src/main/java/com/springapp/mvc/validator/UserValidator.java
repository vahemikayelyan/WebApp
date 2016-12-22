package com.springapp.mvc.validator;

import com.springapp.mvc.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by VAHE on 07-Dec-16.
 */
@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            //errors.rejectValue("password", "password","vvvvvvvvvvv");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            //errors.rejectValue("passwordConfirm", "passwordConfirm", "Not maTch");
        }
    }
}