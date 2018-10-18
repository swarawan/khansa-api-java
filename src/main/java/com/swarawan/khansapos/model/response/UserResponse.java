package com.swarawan.khansapos.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserResponse {

    @ApiModelProperty(example = "319114ac-3f73-49fe-96f5-d2f7e5e52d7f", dataType = "String", required = true, position = 0)
    public String secureId;

    @ApiModelProperty(example = "Rio Swarawan", dataType = "String", required = true, position = 1)
    public String name;

    @ApiModelProperty(example = "swarawan.rio@gmail.com", dataType = "String", required = true, position = 2)
    public String email;

    public UserResponse(String secureId, String name, String email) {
        this.secureId = secureId;
        this.name = name;
        this.email = email;
    }
}
