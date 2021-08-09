package com.example.jieyue.user.controller;

import com.example.jieyue.common.mapper.SysCartMapper;
import com.example.jieyue.user.service.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>购物车功能</p>
 * @author Bosen
 * 2020/11/3 13:06
 */
@RestController
public class UserCartController {
    @Autowired
    UserCartService cartService;
    @Autowired
    SysCartMapper cartMapper;

    @RequestMapping("/user/cart")
    public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request){
        List<Map> cartList = cartService.getCartList(request);
        modelAndView.addObject("cartList",cartList);

        modelAndView.setViewName("user/cart/index");
        return modelAndView;
    }
    
    /**
     * <p>添加至购物车</p>
     */
    @RequestMapping("/user/add-cart")
    public ModelAndView addCart(ModelAndView modelAndView, String id, @RequestParam(defaultValue = "1") int num, HttpServletRequest request){
        if (num<=0){
            modelAndView.addObject("msg","商品数量不合法");
        }else{
            int result = cartService.addCart(Integer.valueOf(id),num,request);
            if (result == 1){
                modelAndView.addObject("msg","已成功添加至购物车");
            }
            if (result == -1){
                modelAndView.addObject("msg","添加至购物车失败");
            }
            if (result == 2){
                modelAndView.addObject("msg","您在此前已将此商品添加至购物车");
            }
        }
        modelAndView.setViewName("redirect:/user/product?id="+id);
        return modelAndView;
    }

    /**
     * <p>移除购物车商品</p>
     */
    @RequestMapping("/user/del-cart")
    public ModelAndView delCart(ModelAndView modelAndView,int id){
        int sql = cartMapper.deleteById(id);
        if (sql == 1){
            modelAndView.addObject("msg","移除购物车商品成功");
        }else{
            modelAndView.addObject("msg","移除购物车商品失败");
        }
        modelAndView.setViewName("redirect:/user/cart");
        return modelAndView;
    }
}
