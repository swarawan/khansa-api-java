package com.swarawan.khansapos.controller.goods;

import com.swarawan.khansapos.base.BaseController;
import com.swarawan.khansapos.model.request.GoodsRequest;
import com.swarawan.khansapos.model.response.GoodsResponse;
import com.swarawan.khansapos.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/goods")
@Api("Goods API")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("Get all goods records")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultVO> getAll() {
        List<GoodsResponse> goodsResponse = goodsService.getAll();
        return abstractResponseHandler(goodsResponse).getResult();
    }

    @ApiOperation("Get specific goods by secure id")
    @GetMapping(value = "/{secure-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultVO> getGoods(@PathVariable String secureId) {
        GoodsResponse goodsResponse = goodsService.getOne(secureId);
        return abstractResponseHandler(goodsResponse).getResult();
    }

    @ApiOperation("Add goods by secure id")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultVO> addGoods(@RequestBody GoodsRequest request) {
        GoodsResponse goodsResponse = goodsService.addGoods(request);
        return abstractResponseHandler(goodsResponse).getResult();
    }

    @ApiOperation("Update goods by secure id")
    @PutMapping(value = "/{secure-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultVO> updateGoods(@PathVariable String secureId,
                                                @RequestBody GoodsRequest request) {
        GoodsResponse goodsResponse = goodsService.updateGoods(secureId, request);
        return abstractResponseHandler(goodsResponse).getResult();
    }
}
