package com.example.jieyue.common.mapper;

import com.example.jieyue.common.entity.SysOrder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SysOrderMapper {
    List<SysOrder> findLimit(int preNum,int sufNum);

    List<SysOrder> findPayLimit(int preNum,int sufNum);

    List<SysOrder> findNotPayLimit(int preNum,int sufNum);

    List<SysOrder> findLimitByMt(int merchantId,int preNum,int sufNum);

    List<SysOrder> findPayLimitByMt(int merchantId,int preNum,int sufNum);

    List<SysOrder> findNotPayLimitByMt(int merchantId,int preNum,int sufNum);

    List<SysOrder> findAll();

    List<SysOrder> findByUser(int user,int preNum,int sufNum);

    List<SysOrder> findByMerchant(int merchant,int num);

    List<SysOrder> findByState(int state);

    SysOrder findByOrderId(String orderId);

    SysOrder findByOrderIdAndMt(String orderId,int merchant);

    List<SysOrder> findByOrderMark(String orderMark);

    int orderCount();

    int payCount();

    int notPayCount();

    int orderCountByUser(int userId);

    int orderCountByMt(int merchantId);

    int payCountByMt(int merchantId);

    int notPayCountByMt(int merchantId);

    // 修改订单状态
    int updateState(long pay_time,String orderMark);

    int updateCodeUrl(String codeUrl,String orderMark);

    int deleteById(int id);

    // 添加订单
    int insert1(String orderId, long createTime, int goodsNum, String orderMark, int orderUser, int orderMerchant,
               BigDecimal orderPrice,int goodsId,String orderNotes,String userAddress,String userName,
               String userPhone,String couponCode,int payWay,float merchantRatio);
    int insert2(String orderId, long createTime, int goodsNum, String orderMark, int orderUser, int orderMerchant,
               BigDecimal orderPrice,int goodsId,String orderNotes,String userAddress,String userName,
               String userPhone,String couponCode,int payWay,String cartId,float merchantRatio);

    // 收益
    float websiteProfitCount();

    Float merchantProfitCount(int merchantId);
}
