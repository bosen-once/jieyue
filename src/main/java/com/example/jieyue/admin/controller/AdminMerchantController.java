package com.example.jieyue.admin.controller;

import com.example.jieyue.admin.service.AdminMerchantService;
import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.common.mapper.SysMtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>商户管理页面</p>
 * @author Bosen
 * 2020/11/8 10:50
 */
@RestController
@RequestMapping("/admin/merchant")
public class AdminMerchantController {
    @Autowired
    AdminMerchantService merchantService;
    @Autowired
    SysMtMapper merchantMapper;

    @RequestMapping("")
    public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request){
        modelAndView.setViewName("/admin/merchant/index");

        int pageSize = 10;
        int pageCount = merchantService.getMtPage(pageSize);
        int num = 1;
        int preNum = 1;
        int nextNum = 1;
        if (request.getParameter("num")!=null){
            num = Integer.parseInt(request.getParameter("num"));
        }
        if (num <= 1){
            preNum = 1;
            nextNum = 2;
        }else{
            preNum = num-1;
            if (num>=pageCount-1){
                nextNum = pageCount;
            }else{
                nextNum = num+1;
            }
        }
        List<SysMt> mtList = merchantService.getMtInfo(num,pageSize);
        if (mtList.size()<=10){
            nextNum = num;
        }
        modelAndView.addObject("mtList",mtList);
        modelAndView.addObject("pageCount",pageCount);
        modelAndView.addObject("nextNum",nextNum);
        modelAndView.addObject("preNum",preNum);
        modelAndView.addObject("num",num);

        return modelAndView;
    }

    @RequestMapping("update-ratio")
    public ModelAndView updateRatio(ModelAndView modelAndView,String ratio,int id){
        try {
            float ratioFloat = Float.valueOf(ratio);
            if (ratioFloat > 1.0 || ratioFloat < 0.1 || !checkFloat(ratioFloat)){
                modelAndView.addObject("msg","费率应在0.1~1.0之间（一位小数）");
            }else{
                if (merchantMapper.updateRatio(id,ratioFloat)==1){
                    modelAndView.addObject("msg","修改成功");
                }else{
                    modelAndView.addObject("msg","修改失败");
                }
            }
        }catch (Exception e){
            modelAndView.addObject("msg","输入的费率不合法");
        }

        modelAndView.setViewName("redirect:/admin/merchant");
        return modelAndView;
    }

    /*
     * 删除商户
     */
    @RequestMapping("delete-merchant")
    public ModelAndView deleteMerchant(ModelAndView modelAndView,int id){
        if (merchantService.deleteMerchant(id)==1){
            modelAndView.addObject("msg","删除商户成功！");
        }else{
            modelAndView.addObject("msg","删除商户失败！");
        }
        modelAndView.setViewName("redirect:/admin/merchant");
        return modelAndView;
    }

    /*
     * 修改商户状态
     */
    @RequestMapping("update-merchant")
    public ModelAndView updateMerchant(ModelAndView modelAndView,String email,int state){
        if (merchantService.updateMerchantState(email,state)==1){
            modelAndView.addObject("msg","操作成功！");
        }else{
            modelAndView.addObject("msg","操作失败！");
        }
        modelAndView.setViewName("redirect:/admin/merchant");
        return modelAndView;
    }

    /*
     * 检查费率是否超过了一位小数
     */
    public boolean checkFloat(float ratio){
        char[] c = String.valueOf(ratio).split(".")[1].toCharArray();
        if (c.length > 1){
            return true;
        }
        return false;
    }
}
