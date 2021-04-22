package com.example.jieyue.common.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p>文件操作工具类</p>
 * @author Bosen
 * 2020/11/5 22:55
 */
@Component
public class FileUtil {
    
    String classpath;
    public FileUtil() throws FileNotFoundException {
        this.classpath = ResourceUtils.getFile("classpath:/static").getPath()+"\\";
    }

    /**
     * 文件上传逻辑处理
     *
     * @return
     * null 上传失败
     * 文件名 上传成功
     */
    public String upFile(MultipartFile file, RedirectAttributes redirectAttributes,
                         HttpServletRequest request,String url,String filename) {
        // MultipartFile是对当前上传的文件的封装，当要同时上传多个文件时，可以给定多个MultipartFile参数(数组)
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return null;
        }
        // 取文件格式后缀名
        // String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));

        File savefile1 = new File(classpath+url);
        //判断上传文件的保存目录是否存在
        if (!savefile1.exists() && !savefile1.isDirectory()) {
            System.out.println(classpath+"目录不存在，需要创建");
            //创建目录
            savefile1.mkdir();
        }
        String suffix = this.getSuffixName(file.getOriginalFilename());
        try {
            // FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
            // 加入原工程static目录
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(savefile1+"/"+filename+"."+suffix));// 复制临时文件到指定目录下
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url+filename+"."+suffix;
    }

    /*
     * 获取文件后缀名
     */
    public String getSuffixName(String filename){
        String[] strArray = filename.split("\\.");
        int suffixIndex = strArray.length -1;
        return strArray[suffixIndex];
    }

    /*
     * 删除文件
     */
    public void deleteFile(String url){
        File file1 = new File(classpath+url);
        file1.delete();
    }

}
