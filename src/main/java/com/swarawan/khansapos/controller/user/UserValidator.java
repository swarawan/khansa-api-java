package com.swarawan.khansapos.controller.user;

import com.swarawan.khansapos.model.request.UserRequest;
import com.swarawan.khansapos.utils.StringUtils;
import com.swarawan.khansapos.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
class UserValidator {

    @Autowired
    private MessageSource messageSource;

    private String getErrorName() {
        return null != messageSource ?
                messageSource.getMessage("error.name.format", null, LocaleContextHolder.getLocale())
                : StringUtils.BLANK;
    }

    private String getErrorFieldRequired() {
        return null != messageSource ?
                messageSource.getMessage("error.field.required", null, LocaleContextHolder.getLocale())
                : StringUtils.BLANK;
    }

    String validateForm(UserRequest request) {
        if (request.name.isEmpty())
            return String.format(getErrorFieldRequired(), "Name");
        else if (request.email.isEmpty())
            return String.format(getErrorFieldRequired(), "Email");
        else if (!ValidatorUtils.isEmailValid(request.email))
            return String.format(getErrorName(), "Email");
        else
            return StringUtils.BLANK;
    }
}
