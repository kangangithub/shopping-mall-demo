package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.Result;
import com.example.model.Product;
import com.example.service.OrderService;
import com.example.service.ProductService;
import com.example.util.RedisUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author Akang
 * @since 2019-05-22
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private RedisUtils redisUtils;
    @Resource
    private OrderService orderService;
    @Resource
    private ProductService productService;

    @RequestMapping("/init")
    public Result init(@RequestParam String productId) {
        Product product = productService.getOne(new QueryWrapper<Product>().eq("product_id", productId));
        redisUtils.set(productId, product.getProductNumber().toString(), 0);
        return Result.success("初始化成功", redisUtils.get(productId, 0));
    }

    @RequestMapping("/snapUp")
    public Result snapUp(@RequestParam String productId, @RequestParam Long userId) {
        return orderService.createOrder(productId, userId);
    }
}
