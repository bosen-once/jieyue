package com.example.jieyue.common.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GiteeImgBedUtils {
    /**
     * 码云私人令牌
     */
    private static final String ACCESS_TOKEN = "98016b93bd85d836c5158bf340c32d35";

    /**
     * 码云个人空间名
     */
    private static final String OWNER = "bosen-once";

    /**
     * 上传指定仓库
     */
    private static final String REPO = "cloud-disk";

    /**
     * 图片访问前缀
     */
    public static final String PRE =
            "https://gitee.com/" + OWNER + "/" + REPO + "/raw/master/";

    /**
     * 新建(POST)、获取(GET)、删除(DELETE)文件：()中指的是使用对应的请求方式
     * %s =>仓库所属空间地址(企业、组织或个人的地址path)  (owner)
     * %s => 仓库路径(repo)
     * %s => 文件的路径(path)
     */
    private static final String API_CREATE_POST =
            "https://gitee.com/api/v5/repos/%s/%s/contents/%s";

    /**
     * 用于提交描述
     */
    private static final String ADD_MESSAGE = "add file";
    private static final String DEL_MESSAGE = "del file";

    /**
     * <p>上传文件</p>
     * @param multipartFile 文件
     * @return 返回文件下载路径后缀
     */
    public static String upload(String path, MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        if(originalFilename == null){
            return null;
        }
        String targetUrl = GiteeImgBedUtils.createUploadFileUrl(path, originalFilename);
        // 请求体封装
        Map<String, Object> map = null;
        try {
            map = GiteeImgBedUtils.getUploadBodyMap(multipartFile.getBytes());
        } catch (IOException e) {
            return null;
        }
        // 借助HttpUtil工具类发送POST请求
        String jsonResult = HttpUtil.post(targetUrl, map);
        // 解析响应JSON字符串
        JSONObject jsonObj = JSONUtil.parseObj(jsonResult);
        // 请求失败
        if(jsonObj.getObj("commit") == null){
            return null;
        }
        //请求成功：返回下载地址
        JSONObject content = JSONUtil.parseObj(jsonObj.getObj("content"));
        return content.getObj("path").toString();
    }

    /**
     * <p>删除</p>
     */
    public static boolean delete(String path) {
        if(path.contains("default")) {
            return true;
        }
        try {
            String targetUrl = GiteeImgBedUtils.deleteFileUrl(path);
            Map<String, Object> map = getDeleteBodyMap(path);
            // 发送delete请求
            String jsonResult = HttpRequest.delete(targetUrl).form(map).execute().body();
            // 解析响应JSON字符串
            JSONObject jsonObj = JSONUtil.parseObj(jsonResult);
            return jsonObj.getObj("commit") != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * <p>获取sha信息</p>
     */
    private static String getShaInfo(String path) {
        String targetUrl = GiteeImgBedUtils.deleteFileUrl(path);
        Map<String, Object> map = getFileInfoBodyMap();
        // 借助HttpUtil工具类发送get请求
        String jsonResult = HttpUtil.get(targetUrl, map);
        // 解析响应JSON字符串
        JSONObject jsonObj = JSONUtil.parseObj(jsonResult);
        if (jsonObj.getObj("sha") == null) {
            return null;
        }
        return jsonObj.getObj("sha").toString();
    }

    /**
     * 生成创建(获取、删除)的指定文件路径
     */
    private static String createUploadFileUrl(String path, String originalFilename){
        //获取文件后缀
        String suffix = getFileSuffix(originalFilename);
        //拼接存储的图片名称
        String fileName = UUID.randomUUID().toString() + suffix;
        //填充请求路径
        return String.format(GiteeImgBedUtils.API_CREATE_POST,
                GiteeImgBedUtils.OWNER,
                GiteeImgBedUtils.REPO,
                path + "/" + fileName);
    }

    /**
     * <p>生成删除链接</p>
     */
    private static String deleteFileUrl(String path) {
        return String.format(GiteeImgBedUtils.API_CREATE_POST,
                GiteeImgBedUtils.OWNER,
                GiteeImgBedUtils.REPO,
                path);
    }

    /**
     * 获取创建文件的请求体map集合：access_token、message、content
     * @param multipartFile 文件字节数组
     * @return 封装成map的请求体集合
     */
    private static Map<String,Object> getUploadBodyMap(byte[] multipartFile){
        HashMap<String, Object> bodyMap = new HashMap<>(3);
        bodyMap.put("access_token", GiteeImgBedUtils.ACCESS_TOKEN);
        bodyMap.put("message", GiteeImgBedUtils.ADD_MESSAGE);
        bodyMap.put("content", Base64.encode(multipartFile));
        return bodyMap;
    }

    /**
     * 获取删除文件的请求体map集合：access_token、path、sha、message
     * @return 封装成map的请求体集合
     */
    private static Map<String,Object> getDeleteBodyMap(String path){
        HashMap<String, Object> bodyMap = new HashMap<>(3);
        bodyMap.put("access_token", GiteeImgBedUtils.ACCESS_TOKEN);
        bodyMap.put("message", GiteeImgBedUtils.DEL_MESSAGE);
        bodyMap.put("sha", getShaInfo(path));
        return bodyMap;
    }

    /**
     * 获取sha的请求体map集合：access_token
     * @return 封装成map的请求体集合
     */
    private static Map<String,Object> getFileInfoBodyMap(){
        HashMap<String, Object> bodyMap = new HashMap<>(3);
        bodyMap.put("access_token", GiteeImgBedUtils.ACCESS_TOKEN);
        return bodyMap;
    }

    /**
     * 获取文件名的后缀，如：changlu.jpg => .jpg
     * @return 文件后缀名
     */
    private static String getFileSuffix(String fileName) {
        return fileName.contains(".") ? fileName.substring(fileName.indexOf('.')) : null;
    }
}