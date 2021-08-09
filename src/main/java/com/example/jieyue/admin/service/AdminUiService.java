package com.example.jieyue.admin.service;

import com.example.jieyue.common.mapper.SysUiMapper;
import com.example.jieyue.common.utils.DateUtil;
import com.example.jieyue.common.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class AdminUiService {
    @Autowired
    FileUtil fileUtil;
    @Autowired
    DateUtil dateUtil;
    @Autowired
    SysUiMapper uiMapper;

    /**
     * <p>文件上传逻辑处理</p>
     * @return
     * null 上传失败
     * 文件名 上传成功
     */
    public String upImage(MultipartFile file, RedirectAttributes redirectAttributes,
                         HttpServletRequest request, String url,int weight,int height) {
        // 设置filename  文件名由年月日时分秒以及六位随机数组成
        String filename = dateUtil.getNMDHIS()+Math.round(Math.random()*(999999-100000)+100000);
        // 接收文件工具类返回的文件位置
        String result = fileUtil.upFile(file,redirectAttributes,request,url,filename);
        if (result==null){
            return null;
        }else{
            if (uiMapper.findByMark(weight,height) == null) {
                int insertRes = uiMapper.insert(result, weight, height);
                if (insertRes == 1) {
                    return filename;
                } else {
                    // sql语句执行失败，将已上传的图片移除
                    fileUtil.deleteFile(result);
                    return null;
                }
            }else{
                // 删除旧图片
                fileUtil.deleteFile(uiMapper.findByMark(weight,height).getUrl());
                // 更改图片
                int updateRes = uiMapper.updateUrl(result,weight,height);
                if (updateRes == 1) {
                    return filename;
                } else {
                    // sql语句执行失败，将已上传的图片移除
                    fileUtil.deleteFile(result);
                    return null;
                }
            }
        }
    }

    /**
     * <p>删除海报逻辑处理</p>
     */
    public boolean delImg(int width,int height){
        if (uiMapper.findByMark(width,height)==null){
            return false;
        }else{
            fileUtil.deleteFile(uiMapper.findByMark(width,height).getUrl());
            int delResult = uiMapper.deleteByMark(width,height);
            if (delResult == 1) {
                return true;
            }
            return false;
        }
    }
}
