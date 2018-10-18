package com.swarawan.khansapos.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GoodsResponse {

    @ApiModelProperty(example = "2e6306f2-bc3b-4dfd-8411-e02b95ef70c2", dataType = "String", required = true, position = 0)
    public String secureId;

    @ApiModelProperty(example = "Kacang Panjang", dataType = "String", required = true, position = 1)
    public String name;

    @ApiModelProperty(example = "5000", dataType = "Integer", required = true, position = 2)
    public Integer price;

    @ApiModelProperty(example = "10", dataType = "Integer", required = true, position = 3)
    public Integer stock;

    @ApiModelProperty(example = "true", dataType = "Boolean", required = true, position = 4)
    public Boolean available;

    public GoodsResponse(String secureId, String name, Integer price, Integer stock, Boolean available) {
        this.secureId = secureId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.available = available;
    }
}
