package com.example.jieyue.user.controller;

import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.user.service.UserShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * <p>店铺页面控制器类</p>
 * @author Bosen
 * 2020/11/2 20:26
 */
@RestController
public class UserShopController {
    @Autowired
    UserShopService shopService;

    /*
     * 店铺首页
     */
    @RequestMapping("/user/shop")
    public ModelAndView index(ModelAndView modelAndView,int id,@RequestParam(defaultValue = "1") int page){
        // 获取商户信息
        SysMt merchant = shopService.getMerchantInfo(id);
        modelAndView.addObject("merchant",merchant);
        // 获取商户商品信息
        Map<Integer,SysGoods> goodsMap = shopService.getGoodsList(id,page,12);
        modelAndView.addObject("goodsMap",goodsMap);
        // 获取随机商品信息
        Map<Integer,SysGoods> randGoodsMap = shopService.getRandGoodsMap(id,12);
        modelAndView.addObject("randGoodsMap",randGoodsMap);
        // 当前页数和总页数
        modelAndView.addObject("page",page);
        modelAndView.addObject("allPage",shopService.getAllPage(id,12));

        modelAndView.setViewName("user/shop/index");
        return modelAndView;
    }
}
