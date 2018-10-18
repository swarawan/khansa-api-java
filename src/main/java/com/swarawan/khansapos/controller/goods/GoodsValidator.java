package com.swarawan.khansapos.controller.goods;

import com.swarawan.khansapos.model.request.GoodsRequest;
import com.swarawan.khansapos.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
class GoodsValidator {

    @Autowired
    private MessageSource messageSource;

    private String getErrorFieldRequired() {
        return null != messageSource ?
                messageSource.getMessage("error.field.required", null, LocaleContextHolder.getLocale()) : StringUtils.BLANK;
    }

    String validateForm(GoodsRequest request) {
        if (request.name.isEmpty())
            return String.format(getErrorFieldRequired(), "Name");
        else
            return StringUtils.BLANK;
    }
}
