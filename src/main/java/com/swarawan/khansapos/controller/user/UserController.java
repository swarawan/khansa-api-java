package com.swarawan.khansapos.controller.user;

import com.swarawan.khansapos.base.BaseController;
import com.swarawan.khansapos.model.request.UserRequest;
import com.swarawan.khansapos.model.response.UserResponse;
import com.swarawan.khansapos.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Api("User API")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation("Get all user records")
    @GetMapping(value = "/all")
    public ResponseEntity<ResultVO> getAll() {
        List<UserResponse> userResponse = userService.getAll();
        return abstractResponseHandler(userResponse).getResult();
    }

    @ApiOperation("Get specific user by secure id")
    @GetMapping(value = "/{secure-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultVO> getUser(@PathVariable String secureId) {
        UserResponse userResponse = userService.getOne(secureId);
        return abstractResponseHandler(userResponse).getResult();
    }

    @ApiOperation("Update user by secure id")
    @PutMapping(value = "/{secure-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultVO> updateUser(@PathVariable String secureId,
                                               @RequestBody UserRequest request) {
        UserResponse userResponse = userService.updateUser(secureId, request);
        return abstractResponseHandler(userResponse).getResult();
    }
}