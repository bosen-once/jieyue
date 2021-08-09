package com.example.jieyue.merchant.service;

import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.common.entity.SysMtUi;
import com.example.jieyue.common.mapper.SysMtMapper;
import com.example.jieyue.common.mapper.SysMtUiMapper;
import com.example.jieyue.common.utils.DateUtil;
import com.example.jieyue.common.utils.FileUtil;
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
    FileUtil fileUtil;
    @Autowired
    DateUtil dateUtil;
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
        return mtUiMapper.findByMark(width,height,getMtId(session));
    }
    
    /**
     * <p>删除</p>
     */
    public int delHomeImg(int id){
        String url = mtUiMapper.findById(id).getUrl();
        int sql = mtUiMapper.deleteById(id);
        if (sql==1){
            fileUtil.deleteFile(url);
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

        // 设置filename  文件名由年月日时分秒以及六位随机数组成
        String filename = dateUtil.getNMDHIS()+Math.round(Math.random()*(999999-100000)+100000);
        // 接收文件工具类返回的文件位置
        String imgUrl = fileUtil.upFile(img,redirectAttributes,request,
                "/data/mtui/"+id+"/",filename);
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
                fileUtil.deleteFile(sysMtUi.getUrl());
            }else{
                // sql语句执行失败，将已上传的新图删除
                fileUtil.deleteFile(imgUrl);
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
        // 设置filename  文件名由年月日时分秒以及六位随机数组成
        String filename = dateUtil.getNMDHIS()+Math.round(Math.random()*(999999-100000)+100000);
        String headerUrl = fileUtil.upFile(img,redirectAttributes,request,"/data/header/merchant/",filename);
        int sql = merchantMapper.updateHeader(merchant.getId(),headerUrl);
        if (sql!=1){
            fileUtil.deleteFile(headerUrl);
            return -1;
        }else{
            if (!merchant.getHeader().equals("/lib/merchant/images/2.png")){
                fileUtil.deleteFile(merchant.getHeader());
            }
            // 修改会话信息
            merchant.setHeader(headerUrl);
            request.getSession().setAttribute("merchant",merchant);
        }
        return 1;
    }
}
