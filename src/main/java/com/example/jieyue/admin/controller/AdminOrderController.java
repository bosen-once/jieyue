package com.example.jieyue.admin.controller;

import com.example.jieyue.admin.service.AdminOrderService;
import com.example.jieyue.common.entity.SysOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>订单管理控制器</p>
 * @author Bosen
 * 2020/12/1 13:02
 */
@RestController
public class AdminOrderController {
    @Autowired
    AdminOrderService orderService;

    @RequestMapping("/admin/order")
    public ModelAndView index(ModelAndView modelAndView,@RequestParam(defaultValue = "2")int flag, @RequestParam(defaultValue = "1")int page){
        // 获取订单信息
        List<SysOrder> orderList = orderService.getOrderList(page,18,flag);
        modelAndView.addObject("orderList",orderList);

        // 获取订单总页数
        int allPage = orderService.getAllPage(flag,18);
        modelAndView.addObject("page",page);
        modelAndView.addObject("flag",flag);
        modelAndView.addObject("allPage",allPage);

        modelAndView.setViewName("admin/order/index");
        return modelAndView;
    }

    @RequestMapping("/admin/search-order")
    public ModelAndView searchOrder(ModelAndView modelAndView,String order){
        // 获取订单信息
        List<SysOrder> orderList = orderService.getOrderById(order);
        modelAndView.addObject("orderList",orderList);

        // 获取订单总页数
        modelAndView.addObject("page",1);
        modelAndView.addObject("flag",2);
        modelAndView.addObject("allPage",1);

        modelAndView.setViewName("admin/order/index");
        return modelAndView;
    }
}
