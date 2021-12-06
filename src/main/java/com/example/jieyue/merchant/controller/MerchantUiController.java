package com.example.jieyue.merchant.controller;

import com.example.jieyue.common.entity.SysMtUi;
import com.example.jieyue.merchant.service.MerchantUiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * <p>店铺样式设计</p>
 * @author Bosen
 * 2020/11/8 16:29
 */
@RestController
public class MerchantUiController {
    @Autowired
    MerchantUiService uiService;
    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/merchant/ui")
    public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request){
        modelAndView.setViewName("merchant/ui/index");
        // 商户用于商城首页宣传的海报图片路径
        SysMtUi homeImg = uiService.getHomeImg(400,320,request.getSession());
        modelAndView.addObject("homeImg",homeImg);
        SysMtUi lowImg = uiService.getHomeImg(600,310,request.getSession());
        modelAndView.addObject("lowImg",lowImg);

        return modelAndView;
    }
    
    /**
     * <p>删除</p>
     */
    @RequestMapping("/merchant/del-home-img")
    public ModelAndView delHomeImg(ModelAndView modelAndView,int id){
        int res = uiService.delHomeImg(id);
        switch (res){
            case 1:
                modelAndView.addObject("msg","图片删除成功");
                clearCache();
                break;
            case 0:
                modelAndView.addObject("msg","图片删除失败");
                break;
            default:
                break;
        }
        modelAndView.setViewName("redirect:ui");
        return modelAndView;
    }
    
    /**
     * <p>修改或添加商户在商城主页的宣传海报</p>
     */
    @RequestMapping("/merchant/update-home-img")
    public ModelAndView updateHomeImg(ModelAndView modelAndView, HttpServletRequest request, MultipartFile img, RedirectAttributes redirectAttributes,int width,int height){

        int res = uiService.updateHomeImg(width,height,request.getSession(),img,redirectAttributes,request);
        switch (res){
            case 1:
                modelAndView.addObject("msg","图片修改成功");
                clearCache();
                break;
            case 0:
                modelAndView.addObject("msg","图片修改失败");
                break;
            default:
                break;
        }
        modelAndView.setViewName("redirect:ui");
        return modelAndView;
    }
    
    /**
     * <p>修改用户头像</p>
     */
    @RequestMapping("/merchant/update-header")
    public ModelAndView updateHeader(RedirectAttributes redirectAttributes,HttpServletRequest request,ModelAndView modelAndView,MultipartFile img){
        if (img.isEmpty()){
            modelAndView.addObject("msg","未选择要修改的头像");
        }else{
            int result = uiService.updateHeard(redirectAttributes,request,img);
            if (result==1){
                modelAndView.addObject("msg","修改logo成功");
                clearCache();
            }else{
                modelAndView.addObject("msg","修改logo失败");
            }
        }
        modelAndView.setViewName("redirect:/merchant/ui");
        return modelAndView;
    }

    /**
     * <p>清首页缓存</p>
     */
    public void clearCache() {
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
    }
}
