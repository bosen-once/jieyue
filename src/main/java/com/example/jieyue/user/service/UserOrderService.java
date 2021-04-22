package com.example.jieyue.user.service;

import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.entity.SysOrder;
import com.example.jieyue.common.mapper.SysGoodsMapper;
import com.example.jieyue.common.mapper.SysOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserOrderService {
    @Autowired
    SysOrderMapper orderMapper;
    @Autowired
    SysGoodsMapper goodsMapper;

    /*
     * 获取用户订单
     */
    public List<SysOrder> getOrderList(int userId,int page,int num){
        return orderMapper.findByUser(userId,(page-1)*num,num);
    }

    /*
     * 获取订单中的商品信息
     */
    public List<SysGoods> getGoodsList(List<SysOrder> orderList){
        List<SysGoods> list = new ArrayList<>();
        for (SysOrder order : orderList) {
            list.add(goodsMapper.findById(order.getGoodsId()));
        }
        return list;
    }

    /*
     * 获取总页数
     */
    public int getAllPage(int userId,int num){
        return (int)Math.ceil((double)orderMapper.orderCountByUser(userId)/(double)num);
    }
}
