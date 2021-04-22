package com.example.jieyue.admin.controller;

import com.example.jieyue.admin.service.AdminNoticeService;
import com.example.jieyue.common.utils.IsEmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminNoticeController {
    @Autowired
    AdminNoticeService noticeService;
    @Autowired
    IsEmptyUtil isEmptyUtil;

    @RequestMapping("/admin/notice")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("admin/notice/index");
        return modelAndView;
    }

    /*
     * 系统消息发送  利用redis异步发送
     */
    @RequestMapping("/admin/send-notice")
    public ModelAndView sendNotice(ModelAndView modelAndView,String title,String context,int type){
        if (isEmptyUtil.strings(title,context)){
            modelAndView.addObject("msg","必填信息不能为空");
        }else{
            noticeService.send(title,context,type);
            modelAndView.addObject("msg","系统消息发送成功");
        }
        modelAndView.setViewName("redirect:/admin/notice");
        return modelAndView;
    }
}
