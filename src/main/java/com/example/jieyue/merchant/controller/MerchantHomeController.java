package com.example.jieyue.merchant.controller;

import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.common.mapper.SysOrderMapper;
import com.example.jieyue.merchant.service.MerchantHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>商户主页</p>
 * @author Bosen
 * 2020/11/8 14:27
 */
@RestController
public class MerchantHomeController {
    @Autowired
    MerchantHomeService homeService;
    @Autowired
    SysOrderMapper orderMapper;

    @RequestMapping({"/merchant/home","/merchant/"})
    public ModelAndView index(ModelAndView modelAndView, HttpSession session){
        SysMt merchant = (SysMt) session.getAttribute("merchant");

        int commentCount = homeService.getCommandCount(merchant.getId());
        int userCount = homeService.getUserCount(merchant.getId());
        int orderCount = homeService.getOrderCount(merchant.getId());
        float profit = 0;
        if (orderMapper.merchantProfitCount(merchant.getId()) != null) {
            profit = orderMapper.merchantProfitCount(merchant.getId());
        }

        modelAndView.addObject("commentCount",commentCount);
        modelAndView.addObject("profit",profit);
        modelAndView.addObject("userCount",userCount);
        modelAndView.addObject("orderCount",orderCount);

        modelAndView.setViewName("merchant/home/index");
        return modelAndView;
    }
}
