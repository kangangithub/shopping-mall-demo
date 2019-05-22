package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.Result;
import com.example.model.Order;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Akang
 * @since 2019-05-22
 */
public interface OrderService extends IService<Order> {

    /**
     * 下单成功
     *
     * @param productId 产品ID
     * @param userId 用户ID
     */
    Result createOrder(String productId, Long userId);
}
