package com.swarawan.khansapos.controller.credential;

import com.swarawan.khansapos.model.request.LoginRequest;
import com.swarawan.khansapos.model.request.RegisterRequest;
import com.swarawan.khansapos.utils.StringUtils;
import com.swarawan.khansapos.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
class CredentialValidator {

    @Autowired
    private MessageSource messageSource;

    private String getErrorEmail() {
        return null != messageSource ?
                messageSource.getMessage("error.email.format", null, LocaleContextHolder.getLocale())
                : StringUtils.BLANK;
    }

    private String getErrorFieldRequired() {
        return null != messageSource ?
                messageSource.getMessage("error.field.required", null, LocaleContextHolder.getLocale())
                : StringUtils.BLANK;
    }

    private String getErrorPasswordNotMatch() {
        return null != messageSource ?
                messageSource.getMessage("error.password.match", null, LocaleContextHolder.getLocale())
                : StringUtils.BLANK;
    }

    String validateRegister(RegisterRequest request) {
        if (request.name.isEmpty())
            return String.format(getErrorFieldRequired(), "Name");
        else if (request.email.isEmpty())
            return String.format(getErrorFieldRequired(), "Email");
        else if (request.password.isEmpty())
            return String.format(getErrorFieldRequired(), "Password");
        else if (!ValidatorUtils.isEmailValid(request.email))
            return String.format(getErrorEmail(), "Email");
        else if (!request.password.equals(request.confirmation))
            return getErrorPasswordNotMatch();
        else return StringUtils.BLANK;
    }


    String validateLoginForm(LoginRequest request) {
        if (request.email.isEmpty())
            return String.format(getErrorFieldRequired(), "Email");
        else if (!ValidatorUtils.isEmailValid(request.email))
            return String.format(getErrorEmail(), "Email");
        else return StringUtils.BLANK;
    }
}
