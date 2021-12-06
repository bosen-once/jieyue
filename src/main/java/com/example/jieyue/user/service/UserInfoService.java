package com.example.jieyue.user.service;

import com.example.jieyue.common.entity.SysUser;
import com.example.jieyue.common.mapper.SysUserMapper;
import com.example.jieyue.common.service.MailService;
import com.example.jieyue.common.utils.GiteeImgBedUtils;
import com.example.jieyue.common.utils.IsEmptyUtil;
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

        if (!"".equals(oldPwd) || !"".equals(newPwd) || !"".equals(rePwd)){
            oldPwd = DigestUtils.md5DigestAsHex(oldPwd.getBytes());
            if (!user.getPassword().equals(oldPwd)){
                return 3;
            }
            if (!newPwd.equals(rePwd)){
                return 4;
            }
        }
        if ("".equals(newPwd)){
            newPwd = user.getPassword();
        }else{
            newPwd = DigestUtils.md5DigestAsHex(newPwd.getBytes());
        }
        // 更新头像
        String header = user.getHeader();
        if (!img.isEmpty()){
            header = GiteeImgBedUtils.upload("/data/header/user/", img);
            if (!"".equals(header) && null != header &&
                    !user.getHeader().equals(GiteeImgBedUtils.PRE + "/data/header/user/default.jpg")){
                GiteeImgBedUtils.delete(user.getHeader());
            }
        }
        int sql = sysUserMapper.updateById(username, newPwd, header, email, id);
        if (sql == 1){
            // 更新用户信息
            SysUser newUser = sysUserMapper.selectById(id);
            newUser.setHeader(GiteeImgBedUtils.PRE + newUser.getHeader());
            request.getSession().setAttribute("user", newUser);
            return 1;
        }
        return -1;
    }
}
