package com.example.jieyue.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * <p>数据库表的映射类</p>
 * @author Bosen
 * @date 2021/8/9 22:46
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysOrder {
    private int id;
    private String orderId;
    private long createTime;
    private long payTime;
    private int goodsNum;
    private int orderState;
    private String orderMark;
    private int orderMerchant;
    private int orderUser;
    private int goodsId;
    private BigDecimal orderPrice;
    private String orderNotes;
    private String userAddress;
    private String userName;
    private String couponCode;
    private int payWay;
    private String payCodeUrl;
    private int cartId;
    private float merchantRatio;
    private String userPhone;
}