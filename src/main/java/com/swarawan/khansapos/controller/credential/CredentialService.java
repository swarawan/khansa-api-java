package com.swarawan.khansapos.controller.credential;

import com.swarawan.khansapos.costant.StatusCode;
import com.swarawan.khansapos.entity.User;
import com.swarawan.khansapos.exception.AppException;
import com.swarawan.khansapos.model.request.LoginRequest;
import com.swarawan.khansapos.model.request.RegisterRequest;
import com.swarawan.khansapos.model.response.UserResponse;
import com.swarawan.khansapos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class CredentialService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CredentialValidator credentialValidator;

    UserResponse login(LoginRequest request) {
        String message = credentialValidator.validateLoginForm(request);
        if (!message.isEmpty()) throw new AppException(message, StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        User user = userRepository.findByEmailPassword(request.email, request.password);
        return new UserResponse(
                user.secureId,
                user.name,
                user.email
        );
    }

    UserResponse register(RegisterRequest request) {
        String message = credentialValidator.validateRegister(request);
        if (!message.isEmpty()) throw new AppException(message, StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        User newUser = new User();
        newUser.secureId = UUID.randomUUID().toString();
        newUser.name = request.name;
        newUser.email = request.password;
        newUser.password = request.password;

        userRepository.save(newUser);
        return new UserResponse(
                newUser.secureId,
                newUser.name,
                newUser.email);
    }
}
