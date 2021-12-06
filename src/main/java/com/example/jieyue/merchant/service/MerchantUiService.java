package com.example.jieyue.merchant.service;

import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.common.entity.SysMtUi;
import com.example.jieyue.common.mapper.SysMtMapper;
import com.example.jieyue.common.mapper.SysMtUiMapper;
import com.example.jieyue.common.utils.GiteeImgBedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class MerchantUiService {
    @Autowired
    SysMtUiMapper mtUiMapper;
    @Autowired
    SysMtMapper merchantMapper;

    /**
     * <p>获取当前商户的id值</p>
     * @return
     * -1 无用户的session信息
     */
    public int getMtId(HttpSession session){
        SysMt mtUi = (SysMt) session.getAttribute("merchant");
        if (mtUi == null){
            return -1;
        }
        return mtUi.getId();
    }

    /**
     * <p>获取商户用于商城首页宣传的海报图片对象</p>
     */
    public SysMtUi getHomeImg(int width,int height,HttpSession session){
        SysMtUi ui = mtUiMapper.findByMark(width,height,getMtId(session));
        if (ui != null) {
            ui.setUrl(GiteeImgBedUtils.PRE + ui.getUrl());
        }
        return ui;
    }
    
    /**
     * <p>删除</p>
     */
    public int delHomeImg(int id){
        String url = mtUiMapper.findById(id).getUrl();
        int sql = mtUiMapper.deleteById(id);
        if (sql == 1){
            GiteeImgBedUtils.delete(url);
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * <p>商户商城首页的宣传海报</p>
     */
    public int updateHomeImg(int width, int height, HttpSession session, MultipartFile img,RedirectAttributes redirectAttributes,
                             HttpServletRequest request){
        // 获取当前商户id
        int id = ((SysMt)request.getSession().getAttribute("merchant")).getId();
        SysMtUi sysMtUi = mtUiMapper.findByMark(width,height,id);

        // 接收文件工具类返回的文件位置
        String imgUrl = GiteeImgBedUtils.upload("/data/mtui/" + id, img);
        if (imgUrl==null){
            // 上传图片失败
            return 0;
        }
        if (sysMtUi==null){
            return addHomeImg(imgUrl,width,height,id);
        }else{
            int sql = mtUiMapper.updateUrl(imgUrl,width,height,id);
            if (sql==1){
                // sql语句执行成功，将旧图删除，加入新图
                GiteeImgBedUtils.delete(sysMtUi.getUrl());
            }else{
                // sql语句执行失败，将已上传的新图删除
                GiteeImgBedUtils.delete(imgUrl);
            }
            return sql;
        }
    }
    
    /**
     * <p>增加商户商城首页的宣传海报</p>
     */
    public int addHomeImg(String url,int width, int height,int id){
        return mtUiMapper.insert(url,width,height,id);
    }

    /**
     * <p>修改商户头像</p>
     */
    public int updateHeard(RedirectAttributes redirectAttributes,HttpServletRequest request, MultipartFile img){
        // 获取商户信息
        SysMt merchant = (SysMt) request.getSession().getAttribute("merchant");
        String headerUrl = GiteeImgBedUtils.upload("/data/header/merchant/", img);
        int sql = merchantMapper.updateHeader(merchant.getId(),headerUrl);
        if (sql != 1){
            GiteeImgBedUtils.delete(headerUrl);
            return -1;
        }else{
            GiteeImgBedUtils.delete(merchant.getHeader());
            // 修改会话信息
            merchant.setHeader(GiteeImgBedUtils.PRE + headerUrl);
            request.getSession().setAttribute("merchant",merchant);
        }
        return 1;
    }
}
