package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.Product;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author Akang
 * @since 2019-05-22
 */
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 减库存
     *
     * @param productId 商品名
     * @return
     */
    int reduceRepertory(String productId);
}
