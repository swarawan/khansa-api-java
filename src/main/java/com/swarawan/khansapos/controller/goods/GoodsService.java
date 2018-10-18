package com.swarawan.khansapos.controller.goods;

import com.swarawan.khansapos.costant.StatusCode;
import com.swarawan.khansapos.entity.Goods;
import com.swarawan.khansapos.exception.AppException;
import com.swarawan.khansapos.model.request.GoodsRequest;
import com.swarawan.khansapos.model.response.GoodsResponse;
import com.swarawan.khansapos.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
class GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsValidator goodsValidator;

    List<GoodsResponse> getAll() {
        List<GoodsResponse> allGoods = new ArrayList<>();
        for (Goods goods : goodsRepository.findAll()) {
            allGoods.add(new GoodsResponse(goods.secureId, goods.name, goods.price, goods.stock, goods.available));
        }
        return allGoods;
    }

    GoodsResponse getOne(String secureId) {
        Goods goods = goodsRepository.findBySecureId(secureId);
        return new GoodsResponse(
                goods.secureId,
                goods.name,
                goods.price,
                goods.stock,
                goods.available);
    }

    GoodsResponse addGoods(GoodsRequest request) {
        String message = goodsValidator.validateForm(request);
        if (!message.isEmpty()) throw new AppException(message, StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        Goods updatedGoods = new Goods();
        updatedGoods.secureId = UUID.randomUUID().toString();
        updatedGoods.name = request.name;
        updatedGoods.price = request.price;
        updatedGoods.stock = request.stock;
        updatedGoods.available = request.available;

        goodsRepository.save(updatedGoods);
        return new GoodsResponse(
                updatedGoods.secureId,
                updatedGoods.name,
                updatedGoods.price,
                updatedGoods.stock,
                updatedGoods.available);
    }

    GoodsResponse updateGoods(String secureId, GoodsRequest request) {
        String message = goodsValidator.validateForm(request);
        if (!message.isEmpty()) throw new AppException(message, StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        Goods updatedGoods = goodsRepository.findBySecureId(secureId);
        updatedGoods.name = request.name;
        updatedGoods.price = request.price;
        updatedGoods.stock = request.stock;
        updatedGoods.available = request.available;

        goodsRepository.save(updatedGoods);
        return new GoodsResponse(
                updatedGoods.secureId,
                updatedGoods.name,
                updatedGoods.price,
                updatedGoods.stock,
                updatedGoods.available);
    }
}
