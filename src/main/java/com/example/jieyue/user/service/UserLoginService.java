package com.example.jieyue.user.service;

import com.example.jieyue.common.entity.SysUser;
import com.example.jieyue.common.service.MailService;
import com.example.jieyue.common.service.SysUserService;
import com.example.jieyue.common.utils.IsEmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserLoginService {
    @Autowired
    SysUserService service;
    @Value("${site-url}")
    String sitrUrl;
    @Autowired
    MailService mailService;
    @Autowired
    RedisTemplate redisTemplate;

    // 判空工具类
    IsEmptyUtil isEmptyUtil = IsEmptyUtil.getInstance();
    /**
     * <p>登陆验证逻辑处理</p>
     * @return int
     * 1 登陆成功
     * 0 必填信息不能为空
     *-1 邮箱不存在或密码错误
     */
    public int dologin(String email,String password){
        SysUser user = service.selectByEmail(email);
        // 用户名密码不能为空
        if (isEmptyUtil.strings(email,password)){
            return 0;
        }
        // 不存在该用户
        if (user==null){
            return -1;
        }
        // 密码验证
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        // 密码错误
        if (!user.getPassword().equals(password)){
            return -1;
        }else{
            return 1;
        }
    }
    
    /*
     * 返回用户信息
     */
    public SysUser userInfo(String email){
        return service.getUserInfo(email);
    }

    /**
     * <p>注册逻辑处理</p>
     * @return int
     *-1 注册超时请重试
     * 0 必填信息不能为空
     * 1 处理请求合理，已发送验证码
     * 2 两次密码不一致
     * 3 邮箱格式有误
     * 4 该邮箱已被注册
     */
    public int singup(String email,String username,String password,String repwd){
        // 必填信息不能为空
        if (isEmptyUtil.strings(email,username,password,repwd)){
            return 0;
        }
        // 邮箱格式验证
        if(!mailService.checkEmail(email)){
            System.out.println(email);
            return 3;
        }
        // 两次密码不一致
        if (!password.equals(repwd)){
            return 2;
        }
        // 该邮箱已被注册
        SysUser user = service.selectByEmail(email);
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (user!=null){
            if (user.getMark()!=0){
                return 4;
            }else{
                if (service.update(username,password,email)!=1){
                    return -1;
                }
            }
        }else{
            // 将信息保存，状态置为0未启用

            // 添加信息未成功返回超时
            if (service.insert(username,password,email,0)!=1){
                return -1;
            }
        }
        // 发送注册验证邮件
        Map<String,String> map = new HashMap<>();
        map.put("email",email);
        map.put("title","捷阅网用户注册验证");
        map.put("context","<a href='http://" + this.sitrUrl + "/user/sign-check?email=" + email + "'>点击此链接完成注册验证</a>");

        redisTemplate.opsForList().leftPush("email",map);

        return 1;
    }

    /**
     * 验证邮件确认注册
     * @return int
     *-1 404页面
     * 0 网络超时请重试
     * 1 注册成功，跳转至登陆页面
     */
    public int singCheck(String email){
        SysUser user = service.selectByEmail(email);
        if (user!=null && user.getMark()==0){
            int res = service.updateMark(1,email);
            if (res==1){
                return 1;
            }else{
                return 0;
            }
        }else{
            return -1;
        }
    }
}
