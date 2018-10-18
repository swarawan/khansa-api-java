package com.swarawan.khansapos.controller.credential;

import com.swarawan.khansapos.base.BaseController;
import com.swarawan.khansapos.model.request.LoginRequest;
import com.swarawan.khansapos.model.request.RegisterRequest;
import com.swarawan.khansapos.model.response.UserResponse;
import com.swarawan.khansapos.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/credential")
@Api("Credential API")
public class CredentialController extends BaseController {

    @Autowired
    private CredentialService credentialService;

    @ApiOperation("Login using email and password")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultVO> login(@RequestBody LoginRequest request) {
        UserResponse goodsResponse = credentialService.login(request);
        return abstractResponseHandler(goodsResponse).getResult();
    }

    @ApiOperation("Register user")
    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultVO> register(@RequestBody RegisterRequest request) {
        UserResponse goodsResponse = credentialService.register(request);
        return abstractResponseHandler(goodsResponse).getResult();
    }
}
