package com.swarawan.khansapos.controller.user;

import com.swarawan.khansapos.costant.StatusCode;
import com.swarawan.khansapos.entity.User;
import com.swarawan.khansapos.exception.AppException;
import com.swarawan.khansapos.model.request.UserRequest;
import com.swarawan.khansapos.model.response.UserResponse;
import com.swarawan.khansapos.repository.UserRepository;
import com.swarawan.khansapos.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    List<UserResponse> getAll() {
        List<UserResponse> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(new UserResponse(user.secureId, user.name, user.email));
        }
        return users;
    }

    UserResponse getOne(String secureId) {
        User user = userRepository.findBySecureId(secureId);
        return new UserResponse(user.secureId, user.name, user.email);
    }

    UserResponse updateUser(String secureId, UserRequest request) {
        String message = userValidator.validateForm(request);
        if (!message.isEmpty()) throw new AppException(message, StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        User updatedUser = userRepository.findBySecureId(secureId);
        updatedUser.name = request.name;
        updatedUser.email = request.email;
        updatedUser.password = request.password;
        updatedUser.updatedAt = DateUtils.getDefaultDate(new Date());

        userRepository.save(updatedUser);
        return new UserResponse(secureId, request.name, request.email);
    }
}
