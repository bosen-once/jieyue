package com.example.jieyue.user.controller;

import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.common.entity.SysOrder;
import com.example.jieyue.common.mapper.SysGoodsMapper;
import com.example.jieyue.common.mapper.SysMtMapper;
import com.example.jieyue.common.mapper.SysOrderMapper;
import com.example.jieyue.user.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserPayController {
    @Autowired
    WxPayService wxPayService;
    @Autowired
    SysOrderMapper orderMapper;

    /*
     * 微信支付
     */
    @RequestMapping("/user/pay/wx")
    public ModelAndView wx(ModelAndView modelAndView, String nums, String users, String merchants, String goods, String prices,
                           String notes, String address, String name, String phone, String code,String carts) {
        // 生成orderMark
        String orderMark = wxPayService.getOrderId();
        // 添加订单
        String res = wxPayService.addOrder(nums,orderMark,users,merchants,prices,notes,goods,address,name,phone,code,0,carts);
        if(!res.equals("-1") && !res.equals("0") && !res.equals("")){
            modelAndView.addObject("orderMark",orderMark);
            modelAndView.setViewName("redirect:/user/wxpay/index?mark="+orderMark);
        }
        return modelAndView;
    }

    /**
     * <p>微信支付回调地址</p>
     * @author Bosen
     * 2020/12/4 20:58
     */
    @RequestMapping("/user/wxpay/notify")
    public String wxNotify(HttpServletRequest request) throws Exception {
        return wxPayService.wxNotify(request);
    }

    /*
     * 检查订单状态
     */
    @RequestMapping("/user/check-order-status")
    public boolean checkOrderStatus(String orderMark){
        List<SysOrder> orderList = orderMapper.findByOrderMark(orderMark);
        if (orderList.size()==0){
            return false;
        }else{
            for (SysOrder order : orderList) {
                if (order.getOrderState() != 1){
                    return false;
                }
            }
            return true;
        }
    }

    /*
     * 订单未支付，重新显示二维码给用户支付
     */
    @RequestMapping("/user/wxpay/index")
    public ModelAndView wxNotify(ModelAndView modelAndView,String mark) {
        String codeUrl = orderMapper.findByOrderMark(mark).get(0).getPayCodeUrl();

        modelAndView.addObject("codeUrl",codeUrl);
        modelAndView.addObject("orderMark",mark);

        modelAndView.setViewName("user/pay/wx");
        return modelAndView;
    }

    /*
     * 订单并发安全测试
     */
    @Autowired
    SysGoodsMapper goodsMapper;
    @Autowired
    SysMtMapper merchantMapper;
    @RequestMapping("/user/pay/test")
    @Transactional
    public String test() {
        // 用于测试商品的id值
        int goodsId = 44;
        SysGoods goods = goodsMapper.findById(goodsId);
        if (goods.getStock() > 0){
            // 生成orderMark
            String orderMark = wxPayService.getOrderId();
            // 生成订单号
            String orderId = wxPayService.getOrderId();
            // 获取商户信息
            SysMt merchant = merchantMapper.findById(goods.getMerchant());
            // 执行sql语句
            int sql = orderMapper.insert1(orderId, System.currentTimeMillis(), 1, orderMark, 99,
                    goods.getMerchant(), goods.getPrice(), goodsId, "test", "test",
                    "test", "123456", "test", 0,merchant.getRatio());

            // 将商品库存做相应的减少
            int delStock = goodsMapper.delStock(goodsId,1);

            // 库存检查，库存少于零时回滚
            if (sql != 1 || delStock != 1 || goodsMapper.findById(goodsId).getStock() < 0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

            return "SUCCESS This is /user/pay/test";
        }
        return "ERROR This is /user/pay/test";

    }
}
