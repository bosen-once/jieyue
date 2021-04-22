package com.example.jieyue.common.entity;

import java.math.BigDecimal;

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

    public float getMerchantRatio() {
        return merchantRatio;
    }

    public void setMerchantRatio(float merchantRatio) {
        this.merchantRatio = merchantRatio;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getPayCodeUrl() {
        return payCodeUrl;
    }

    public void setPayCodeUrl(String payCodeUrl) {
        this.payCodeUrl = payCodeUrl;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public String getOrderMark() {
        return orderMark;
    }

    public void setOrderMark(String orderMark) {
        this.orderMark = orderMark;
    }

    public int getOrderMerchant() {
        return orderMerchant;
    }

    public void setOrderMerchant(int orderMerchant) {
        this.orderMerchant = orderMerchant;
    }

    public int getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(int orderUser) {
        this.orderUser = orderUser;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderNotes() {
        return orderNotes;
    }

    public void setOrderNotes(String orderNotes) {
        this.orderNotes = orderNotes;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    private String userPhone;
}