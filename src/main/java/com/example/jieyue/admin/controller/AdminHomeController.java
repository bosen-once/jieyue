package com.example.jieyue.admin.controller;

import com.example.jieyue.common.mapper.SysGoodsMapper;
import com.example.jieyue.common.mapper.SysOrderMapper;
import com.example.jieyue.common.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>后台主页</p>
 * @author Bosen
 * 2020/11/3 20:38
 */
@RestController
public class AdminHomeController {
    @Autowired
    SysOrderMapper orderMapper;
    @Autowired
    SysGoodsMapper goodsMapper;
    @Autowired
    SysUserMapper userMapper;

    @RequestMapping("/admin/home")
    public ModelAndView index(ModelAndView modelAndView){
        float profit = orderMapper.websiteProfitCount();
        int orderCount = orderMapper.payCount();
        int goodsCount = goodsMapper.allGoodsCount();
        int userCount = userMapper.userCount();

        modelAndView.addObject("profit",profit);
        modelAndView.addObject("orderCount",orderCount);
        modelAndView.addObject("goodsCount",goodsCount);
        modelAndView.addObject("userCount",userCount);

        modelAndView.setViewName("admin/home/index");
        return modelAndView;
    }
}
