package com.example.jieyue.user.service;

import com.example.jieyue.common.entity.SysUser;
import com.example.jieyue.common.mapper.SysUserMapper;
import com.example.jieyue.common.service.MailService;
import com.example.jieyue.common.utils.FileUtil;
import com.example.jieyue.common.utils.IsEmptyUtil;
import com.sun.mail.smtp.DigestMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserInfoService {

    @Autowired
    MailService mailService;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    FileUtil fileUtil;

    IsEmptyUtil isEmptyUtil = new IsEmptyUtil();


    /**
     * <p>修改用户信息</p>
     * @return
     *-1 修改信息失败
     * 1 修改信息成功
     * 0 必填信息不能为空
     * 2 邮箱格式不正确
     * 3 原密码错误
     * 4 两次输入的密码不一致
     */
    public int updateInfo(RedirectAttributes redirectAttributes, HttpServletRequest request,MultipartFile img, int id, String username, String email, String oldPwd, String newPwd, String rePwd){
        if (isEmptyUtil.strings(username,email)){
            return 0;
        }
        if (!mailService.checkEmail(email)){
            return 2;
        }

        SysUser user = sysUserMapper.selectById(id);

        if (!oldPwd.equals("") || !newPwd.equals("") || !rePwd.equals("")){
            oldPwd = DigestUtils.md5DigestAsHex(oldPwd.getBytes());
            if (!user.getPassword().equals(oldPwd)){
                return 3;
            }
            if (!newPwd.equals(rePwd)){
                return 4;
            }
        }
        if (newPwd.equals("")){
            newPwd = oldPwd;
        }else{
            newPwd = DigestUtils.md5DigestAsHex(newPwd.getBytes());
        }
        // 更新头像
        String header = user.getHeader();
        if (!img.isEmpty()){
            if (!user.getHeader().equals("/data/header/user/default.jpg")){
                fileUtil.deleteFile(user.getHeader());
            }
            header = fileUtil.upFile(img,redirectAttributes,request,"/data/header/user/",""+id);
        }
        int sql = sysUserMapper.updateById(username,newPwd,header,email,id);
        if (sql==1){
            // 更新用户信息
            request.getSession().setAttribute("user",sysUserMapper.selectById(id));
            return 1;
        }
        return -1;
    }
}
