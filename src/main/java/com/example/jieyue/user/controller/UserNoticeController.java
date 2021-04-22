package com.example.jieyue.user.controller;

import com.example.jieyue.common.entity.SysNotice;
import com.example.jieyue.common.entity.SysUser;
import com.example.jieyue.common.mapper.SysNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserNoticeController {
    @Autowired
    SysNoticeMapper noticeMapper;

    @RequestMapping("/user/notice")
    public ModelAndView index(ModelAndView modelAndView, HttpSession session,@RequestParam(defaultValue = "1") int page){
        SysUser user = (SysUser)session.getAttribute("user");

        List<SysNotice> noticeList =
                noticeMapper.findByReceiveTypeLimit(user.getId(),2,(page-1)*8,8);
        modelAndView.addObject("noticeList",noticeList);

        modelAndView.setViewName("user/notice/index");
        return modelAndView;
    }
    
    /*
     * 删除通知信息
     */
    @RequestMapping("/user/del-notice")
    public ModelAndView delNotice(ModelAndView modelAndView,int id){
        if (noticeMapper.deleteById(id)==1){
            modelAndView.addObject("msg","删除通知信息成功");
        }else{
            modelAndView.addObject("msg","删除通知信息成功失败");
        }

        modelAndView.setViewName("redirect:/user/notice");
        return modelAndView;
    }
}
