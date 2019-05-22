package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.dao.OrderMapper;
import com.example.dao.ProductMapper;
import com.example.model.Order;
import com.example.model.Product;
import com.example.service.OrderService;
import com.example.util.RedisUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Akang
 * @since 2019-05-22
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private RedisUtils redisUtils;
    @Resource
    private ProductMapper productMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Result createOrder(String productId, Long userId) {
        Product product = productMapper.selectOne(new QueryWrapper<Product>().eq("product_id", productId));
        if (StringUtils.compare(redisUtils.get(productId, 0), "0") > 0) {
            // 减缓存
            redisUtils.decr(productId);
            // 减库存
            boolean flag = productMapper.reduceRepertory(productId) == 1;
            if (flag) {
                // 生成订单
                Order order = new Order();
                order.setOrderId("ORDER_" + LocalDateTime.now().toString() + RandomStringUtils.randomAlphabetic(8));
                order.setOrderName("订单1");
                order.setOrderPrice(new BigDecimal(100));
                order.setOrderStatus(1);
                order.setUserId(userId);
                save(order);
                return Result.success("下单成功, 订单号" + order.getOrderId(), order.getOrderId());
            } else {
                // 减库存失败,重置缓存
                redisUtils.incr(productId);
                log.error("用户" + userId + "下单失败, 商品名" + product.getProductName());
                return Result.error("202", "用户" + userId + "下单失败, 商品名" + product.getProductName());
            }
        } else {
            // 商品售罄
            log.error("商品" + product.getProductName() + "已被抢光");
            return Result.error("201", "商品" + product.getProductName() + "已被抢光");
        }
    }
}
