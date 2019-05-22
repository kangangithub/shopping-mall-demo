package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.ProductMapper;
import com.example.model.Product;
import com.example.service.OrderService;
import com.example.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author Akang
 * @since 2019-05-22
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
