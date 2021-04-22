package com.example.jieyue.merchant.controller;

import com.example.jieyue.admin.service.AdminMerchantService;
import com.example.jieyue.admin.service.AdminOrderService;
import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.common.entity.SysOrder;
import com.example.jieyue.merchant.service.MerchantOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MerchantOrderController {
    @Autowired
    MerchantOrderService orderService;

    @RequestMapping("/merchant/order")
    public ModelAndView index(HttpServletRequest request,ModelAndView modelAndView, @RequestParam(defaultValue = "2")int flag, @RequestParam(defaultValue = "1")int page){
        SysMt merchant = (SysMt) request.getSession().getAttribute("merchant");
        // 获取订单信息
        List<SysOrder> orderList = orderService.getOrderList(merchant.getId(),page,18,flag);
        modelAndView.addObject("orderList",orderList);

        // 获取订单总页数
        int allPage = orderService.getAllPage(merchant.getId(),flag,18);
        modelAndView.addObject("page",page);
        modelAndView.addObject("flag",flag);
        modelAndView.addObject("allPage",allPage);

        modelAndView.setViewName("merchant/order/index");
        return modelAndView;
    }

    @RequestMapping("/merchant/search-order")
    public ModelAndView searchOrder(HttpServletRequest request,ModelAndView modelAndView,String order){
        SysMt merchant = (SysMt) request.getSession().getAttribute("merchant");
        // 获取订单信息
        List<SysOrder> orderList = orderService.getOrderById(order,merchant.getId());
        modelAndView.addObject("orderList",orderList);

        // 获取订单总页数
        modelAndView.addObject("page",1);
        modelAndView.addObject("flag",2);
        modelAndView.addObject("allPage",1);

        modelAndView.setViewName("merchant/order/index");
        return modelAndView;
    }
}
