package com.example.jieyue.user.controller;

import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.index.GoodsIndex;
import com.example.jieyue.user.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>关键字模糊查找</p>
 * @author Bosen
 * 2020/11/29 19:48
 */
@RestController
public class UserSearchController {
    @Autowired
    UserSearchService searchService;

    @RequestMapping("/user/search")
    public ModelAndView index(ModelAndView modelAndView,String keyword){
        // 获取返回的商品列表
        List<GoodsIndex> goodsList = searchService.esSearchGoods(keyword);
        modelAndView.addObject("goodsList",goodsList);

        modelAndView.setViewName("user/search/index");
        return modelAndView;
    }
}
