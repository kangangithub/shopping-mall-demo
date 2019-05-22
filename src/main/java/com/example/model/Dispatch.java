package com.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 配送表
 * </p>
 *
 * @author Akang
 * @since 2019-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Dispatch implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 配送ID
     */
    private String dispatchId;

    /**
     * 配送状态:1未出库2配送中3已收货
     */
    private Integer dispatchStatus;

    /**
     * 出库时间
     */
    private LocalDateTime beginTime;

    /**
     * 收货时间
     */
    private LocalDateTime endTime;

    /**
     * 关联订单
     */
    private String orderId;


}
