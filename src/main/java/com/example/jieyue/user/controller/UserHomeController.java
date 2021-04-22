package com.example.jieyue.user.controller;

import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.entity.SysMtUi;
import com.example.jieyue.common.entity.SysUi;
import com.example.jieyue.user.service.UserHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * <p>网页主页控制器类</p>
 * @author Bosen
 * 2020/11/2 20:03
 */
@RestController
public class UserHomeController{
    @Autowired
    UserHomeService homeService;
    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping({"/user/home","/"})
    public ModelAndView home(ModelAndView modelAndView){
        Map<String, SysUi> imgMap = null;
        // 获取热卖商品列表
        Map<String, SysGoods> escGoodsMap = null;
        // 获取新出商品列表
        Map<String, SysGoods> descGoodsMap = null;
        // 随机获取商品列表
        Map<String, SysGoods> randGoodsMap = null;
        // 获取商户宣传店铺的海报
        Map<String, SysMtUi> homeImg = null;
        Map<String, SysMtUi> lowImg = null;

        if (redisTemplate.opsForHash().entries("homePageCache").size() != 0){

            Map map = redisTemplate.opsForHash().entries("homePageCache");

            // 商城主页宣传海报
            imgMap = (Map<String, SysUi>) map.get("imgMap");
            // 获取热卖商品列表
            escGoodsMap = (Map<String, SysGoods>) map.get("escGoodsMap");
            // 获取新出商品列表
            descGoodsMap = (Map<String, SysGoods>) map.get("descGoodsMap");
            // 随机获取商品列表
            randGoodsMap = (Map<String, SysGoods>) map.get("randGoodsMap");
            // 获取商户宣传店铺的海报
            homeImg = (Map<String, SysMtUi>) map.get("homeImg");
            lowImg = (Map<String, SysMtUi>) map.get("lowImg");

        }else{

            // 商城主页宣传海报
            imgMap = homeService.getImage();
            // 获取热卖商品列表
            escGoodsMap = homeService.getEsc(13);
            // 获取新出商品列表
            descGoodsMap = homeService.getDesc(6);
            // 随机获取商品列表
            randGoodsMap = homeService.getRand(12);
            // 获取商户宣传店铺的海报
            homeImg = homeService.getMtImg(400,320,3);
            lowImg = homeService.getMtImg(600,310,3);
        }

        modelAndView.addObject("imgMap",imgMap);
        modelAndView.addObject("escGoodsMap",escGoodsMap);
        modelAndView.addObject("descGoodsMap",descGoodsMap);
        modelAndView.addObject("randGoodsMap",randGoodsMap);
        modelAndView.addObject("homeImg",homeImg);
        modelAndView.addObject("lowImg",lowImg);

        if (redisTemplate.opsForHash().entries("homePageCache").size() == 0){
            // 设置缓存  十分钟内有效
            homeService.setHomeCache(modelAndView);
        }

        modelAndView.setViewName("user/home/index");
        return modelAndView;
    }
}
