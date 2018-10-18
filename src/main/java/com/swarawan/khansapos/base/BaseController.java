package com.swarawan.khansapos.base;

import com.swarawan.khansapos.handler.AbstractResponseHandler;

public class BaseController {

    public AbstractResponseHandler abstractResponseHandler(Object object) {
        return new AbstractResponseHandler() {
            @Override
            protected Object processResponse() {
                return object;
            }
        };
    }
}