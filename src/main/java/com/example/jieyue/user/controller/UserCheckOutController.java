package com.example.jieyue.user.controller;

import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.user.service.UserCheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>支付页面</p>
 * @author Bosen
 * 2020/11/3 9:53
 */
@RestController
public class UserCheckOutController {
    @Autowired
    UserCheckOutService checkOutService;

    @RequestMapping("/user/checkout")
    public ModelAndView index(ModelAndView modelAndView,String ids,@RequestParam(defaultValue = "-1")int num){
        if(ids == null || "".equals(ids)){
            modelAndView.addObject("msg","请选择您要结算的商品");
            modelAndView.setViewName("redirect:/user/cart");
        }else{
            // 获取商品信息
            Map<SysGoods,Integer> goodsMap = checkOutService.getGoodsInfo(ids);
            modelAndView.addObject("goodsMap",goodsMap);
            // 获取对应的购物车id
            List<Integer> carts = checkOutService.getCartIds(ids);
            modelAndView.addObject("carts",carts);
            // 总金额计算
            BigDecimal allPrice = checkOutService.getGoodsNumPrice(goodsMap);
            modelAndView.addObject("allPrice",allPrice);

            modelAndView.setViewName("user/checkout/index");
        }
        return modelAndView;
    }
}
