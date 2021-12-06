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

    /**
     * <p>获取订单信息</p>
     */
    public List<SysOrder> getOrderList(int merchantId,int page, int num, int flag){
        switch (flag){
            // 未支付
            case 0:
                return orderMapper.findNotPayLimitByMt(merchantId,(page-1)*num,num);
            // 已支付
            case 1:
                return orderMapper.findPayLimitByMt(merchantId,(page-1)*num,num);
            // 全部订单
            case 2:
                return orderMapper.findLimitByMt(merchantId,(page-1)*num,num);
            default:
                break;
        }
        return null;
    }

    /**
     * <p>获取总页数</p>
     */
    public int getAllPage(int merchantId,int flag,int num){
        switch (flag){
            // 未支付
            case 0:
                return (int)Math.ceil((double)orderMapper.notPayCountByMt(merchantId)/(double)num);
            // 已支付
            case 1:
                return (int)Math.ceil((double)orderMapper.payCountByMt(merchantId)/(double)num);
            // 全部订单
            case 2:
                return (int)Math.ceil((double)orderMapper.orderCountByMt(merchantId)/(double)num);
            default:
                break;
        }
        return 1;
    }

    /**
     * <p>通过订单号查找</p>
     */
    public List<SysOrder> getOrderById(String orderId,int merchant){
        SysOrder order = orderMapper.findByOrderIdAndMt(orderId,merchant);
        List<SysOrder> list = new ArrayList<>();
        if (order != null){
            list.add(order);
        }
        return list;
    }
}
