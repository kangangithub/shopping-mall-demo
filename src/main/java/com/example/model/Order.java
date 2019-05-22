package com.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Akang
 * @since 2019-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 订单名
     */
    private String orderName;

    /**
     * 订单价格
     */
    private BigDecimal orderPrice;

    /**
     * 订单状态:1未支付2已支付3已超时
     */
    private Integer orderStatus;

    /**
     * 配送ID
     */
    private String dispatchId;

    /**
     * 用户ID
     */
    private Long userId;
}
