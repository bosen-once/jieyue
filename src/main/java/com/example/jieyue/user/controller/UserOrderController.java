package com.example.jieyue.user.controller;

import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.entity.SysOrder;
import com.example.jieyue.common.entity.SysUser;
import com.example.jieyue.user.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>我的收藏列表</p>
 * @author Bosen
 * 2020/11/3 12:57
 */
@RestController
public class UserOrderController {
    @Autowired
    UserOrderService orderService;

    @RequestMapping("/user/order")
    public ModelAndView index(HttpServletRequest request,ModelAndView modelAndView,@RequestParam(defaultValue = "1") int page){
        SysUser user = (SysUser) request.getSession().getAttribute("user");
        List<SysOrder> orderList = orderService.getOrderList(user.getId(),page,10);
        List<SysGoods> goodsList = orderService.getGoodsList(orderList);
        int allPage = orderService.getAllPage(user.getId(),10);

        modelAndView.addObject("orderList",orderList);
        modelAndView.addObject("goodsList",goodsList);
        modelAndView.addObject("page",page);
        modelAndView.addObject("allPage",allPage);

        modelAndView.setViewName("user/order/index");
        return modelAndView;
    }
}
