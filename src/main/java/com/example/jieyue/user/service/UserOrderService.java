package com.example.jieyue.user.service;

import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.entity.SysOrder;
import com.example.jieyue.common.mapper.SysGoodsMapper;
import com.example.jieyue.common.mapper.SysOrderMapper;
import com.example.jieyue.common.utils.GiteeImgBedUtils;
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

    /**
     * <p>获取用户订单</p>
     */
    public List<SysOrder> getOrderList(int userId,int page,int num){
        return orderMapper.findByUser(userId,(page-1)*num,num);
    }

    /**
     * <p>获取订单中的商品信息</p>
     */
    public List<SysGoods> getGoodsList(List<SysOrder> orderList){
        List<SysGoods> list = new ArrayList<>();
        for (SysOrder order : orderList) {
            SysGoods goods = goodsMapper.findById(order.getGoodsId());
            goods.setImg(GiteeImgBedUtils.PRE + goods.getImg());
            list.add(goods);
        }
        return list;
    }

    /**
     * <p>获取总页数</p>
     */
    public int getAllPage(int userId,int num){
        return (int)Math.ceil((double)orderMapper.orderCountByUser(userId)/(double)num);
    }
}
