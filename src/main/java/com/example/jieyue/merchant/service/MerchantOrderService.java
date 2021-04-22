package com.example.jieyue.merchant.service;

import com.example.jieyue.common.entity.SysOrder;
import com.example.jieyue.common.mapper.SysOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantOrderService {
    @Autowired
    SysOrderMapper orderMapper;

    /*
     * 获取订单信息
     */
    public List<SysOrder> getOrderList(int merchantId,int page, int num, int flag){
        switch (flag){
            case 0:// 未支付
                return orderMapper.findNotPayLimitByMt(merchantId,(page-1)*num,num);
            case 1:// 已支付
                return orderMapper.findPayLimitByMt(merchantId,(page-1)*num,num);
            case 2:// 全部订单
                return orderMapper.findLimitByMt(merchantId,(page-1)*num,num);
        }
        return null;
    }

    /*
     * 获取总页数
     */
    public int getAllPage(int merchantId,int flag,int num){
        switch (flag){
            case 0:// 未支付
                return (int)Math.ceil((double)orderMapper.notPayCountByMt(merchantId)/(double)num);
            case 1:// 已支付
                return (int)Math.ceil((double)orderMapper.payCountByMt(merchantId)/(double)num);
            case 2:// 全部订单
                return (int)Math.ceil((double)orderMapper.orderCountByMt(merchantId)/(double)num);
        }
        return 1;
    }

    /*
     * 通过订单号查找
     */
    public List<SysOrder> getOrderById(String orderId,int merchant){
        SysOrder order = orderMapper.findByOrderIdAndMt(orderId,merchant);
        List<SysOrder> list = new ArrayList<>();
        if (order!=null){
            list.add(order);
        }
        return list;
    }
}
