package com.example.jieyue.admin.service;

import com.example.jieyue.common.entity.SysOrder;
import com.example.jieyue.common.mapper.SysOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminOrderService {
    @Autowired
    SysOrderMapper orderMapper;

    /*
     * 获取订单信息
     */
    public List<SysOrder> getOrderList(int page,int num,int flag){
        switch (flag){
            case 0:// 未支付
                return orderMapper.findNotPayLimit((page-1)*num,num);
            case 1:// 已支付
                return orderMapper.findPayLimit((page-1)*num,num);
            case 2:// 全部订单
                return orderMapper.findLimit((page-1)*num,num);
        }
        return null;
    }

    /*
     * 通过订单号查找
     */
    public List<SysOrder> getOrderById(String orderId){
        SysOrder order = orderMapper.findByOrderId(orderId);
        List<SysOrder> list = new ArrayList<>();
        if (order!=null){
            list.add(order);
        }
        return list;
    }

    /*
     * 获取总页数
     */
    public int getAllPage(int flag,int num){
        switch (flag){
            case 0:// 未支付
                return (int)Math.ceil((double)orderMapper.notPayCount()/(double)num);
            case 1:// 已支付
                return (int)Math.ceil((double)orderMapper.payCount()/(double)num);
            case 2:// 全部订单
                return (int)Math.ceil((double)orderMapper.orderCount()/(double)num);
            case 3:
                return 1;
        }
        return 1;
    }
}
