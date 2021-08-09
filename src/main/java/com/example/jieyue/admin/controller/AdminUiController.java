package com.example.jieyue.admin.controller;

import com.example.jieyue.admin.service.AdminUiService;
import com.example.jieyue.common.entity.SysUi;
import com.example.jieyue.user.service.UserHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>网页图片UI设置</p>
 * @author Bosen
 * 2020/11/4 21:00
 */
@RestController
public class AdminUiController {
    @Autowired
    AdminUiService adminUiService;
    @Autowired
    UserHomeService userHomeService;

    @RequestMapping("/admin/ui")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("admin/ui/index");
        Map<String, SysUi> imgMap = userHomeService.getImage();
        modelAndView.addObject("imgMap",imgMap);
        return modelAndView;
    }
    
    /**
     * <p>文件上传操作</p>
     */
    @RequestMapping("/admin/up-image")
    public ModelAndView upImage(ModelAndView modelAndView, MultipartFile file, RedirectAttributes redirectAttributes, HttpServletRequest request,int width,int height){
        if (file.getOriginalFilename().isEmpty()){
            modelAndView.addObject("msg","文件上传失败");
        }else{
            String upFileResult = adminUiService.upImage(file,redirectAttributes,request,"/data/library/",width,height);
            if (upFileResult==null){
                modelAndView.addObject("msg","文件上传失败");
            }else{
                modelAndView.addObject("msg","文件上传成功");

            }
        }
        modelAndView.setViewName("redirect:ui");
        return modelAndView;
    }

    /**
     * <p>删除海报</p>
     */
    @RequestMapping("/admin/del-image")
    public ModelAndView delImg(ModelAndView modelAndView,int width,int height){
        modelAndView.setViewName("redirect:ui");
        if (adminUiService.delImg(width,height)){
            modelAndView.addObject("msg","删除成功");
        }else{
            modelAndView.addObject("msg","删除失败");
        }
        return modelAndView;
    }
}
