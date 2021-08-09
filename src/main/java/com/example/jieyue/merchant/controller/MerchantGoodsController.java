package com.example.jieyue.merchant.controller;

import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.merchant.service.MerchantGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>商户商品管理页面</p>
 * @author Bosen
 * 2020/11/8 16:27
 */
@RestController
public class MerchantGoodsController {
    @Autowired
    MerchantGoodsService goodsService;

    /**
     * <p>商品管理页面</p>
     */
    @RequestMapping("/merchant/goods")
    public ModelAndView index(ModelAndView modelAndView, HttpSession session, @RequestParam(defaultValue = "1")int page){

        List<SysGoods> goodsList = goodsService.getMtGoods(session,page,10);
        modelAndView.addObject("goodsList",goodsList);
        // 获取订单总页数
        int allPage = goodsService.getAllPage(10,(SysMt)session.getAttribute("merchant"));
        modelAndView.addObject("page",page);
        modelAndView.addObject("allPage",allPage);

        modelAndView.setViewName("merchant/goods/index");
        return modelAndView;
    }

    /**
     * <p>增加商品</p>
     */
    @RequestMapping("/merchant/add-goods")
    public ModelAndView addGoods(ModelAndView modelAndView, String name, String describe,
                                 String price, MultipartFile img,String stock,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest request){
        // 类型转换
        BigDecimal priceRes = null;
        int stockTemp = 0;
        try {
            stockTemp = Integer.valueOf(stock);
            if (stockTemp==0){
                throw new Exception();
            }
            priceRes = new BigDecimal(price);
        }catch (Exception e){
            modelAndView.addObject("msg","输入的金额或库存不合法");
            modelAndView.setViewName("redirect:goods");
            return modelAndView;
        }

        // 添加商品
        int res = goodsService.addGoods(name, describe,priceRes,img,redirectAttributes,stockTemp,request);
        switch (res){
            case -1:
                modelAndView.addObject("msg","图片上传失败");
                break;
            case 0:
                modelAndView.addObject("msg","网络超时");
                break;
            case 1:
                modelAndView.addObject("msg","添加商品成功");
                break;
            case 2:
                modelAndView.addObject("msg","必填信息不能为空");
                break;
            default:
                break;
        }
        modelAndView.setViewName("redirect:goods");
        return modelAndView;
    }
    
    /**
     * <p>通过id值删除商品</p>
     */
    @RequestMapping("/merchant/del-goods")
    public ModelAndView delGoods(ModelAndView modelAndView,int id){
        int res = goodsService.delGoods(id);
        if (res == 1){
            modelAndView.addObject("msg","删除成功");
        }else{
            modelAndView.addObject("msg","删除失败");
        }
        modelAndView.setViewName("redirect:goods");
        return modelAndView;
    }
    
    /**
     * <p>上架商品</p>
     */
    @RequestMapping("/merchant/put-goods")
    public ModelAndView putGoods(ModelAndView modelAndView,int id){
        if (goodsService.putGoods(id)){
            modelAndView.addObject("msg","上架商品成功");
        }else{
            modelAndView.addObject("msg","上架商品成功");
        }
        modelAndView.setViewName("redirect:goods");
        return modelAndView;
    }

    /**
     * <p>下架商品</p>
     */
    @RequestMapping("/merchant/off-goods")
    public ModelAndView OffGoods(ModelAndView modelAndView,int id){
        if (goodsService.OffGoods(id)){
            modelAndView.addObject("msg","下架商品成功");
        }else{
            modelAndView.addObject("msg","下架商品成功");
        }
        modelAndView.setViewName("redirect:goods");
        return modelAndView;
    }

    /**
     * <p>修改商品信息</p>
     */
    @RequestMapping("/merchant/update-goods")
    public ModelAndView updateGoods(ModelAndView modelAndView, String name, String describe,
                                    String price, MultipartFile img,String stock,
                                    RedirectAttributes redirectAttributes,
                                    HttpServletRequest request,int id){
        // 类型转换
        BigDecimal priceRes = null;
        int stockTemp = 0;
        try {
            stockTemp = Integer.valueOf(stock);
            if (stockTemp==0){
                throw new Exception();
            }
            priceRes = new BigDecimal(price);
        }catch (Exception e){
            modelAndView.addObject("msg","输入的金额或库存不合法");
            modelAndView.setViewName("redirect:goods");
            return modelAndView;
        }
        // 修改商品信息
        int res = goodsService.updateGoods(name, describe,priceRes,img,redirectAttributes,stockTemp,request,id);
        switch (res){
            case -1:
                modelAndView.addObject("msg","图片上传失败");
                break;
            case 0:
                modelAndView.addObject("msg","网络超时");
                break;
            case 1:
                modelAndView.addObject("msg","修改商品成功");
                break;
            case 2:
                modelAndView.addObject("msg","必填信息不能为空");
                break;
            default:
                break;
        }
        modelAndView.setViewName("redirect:goods");
        return modelAndView;
    }
}
