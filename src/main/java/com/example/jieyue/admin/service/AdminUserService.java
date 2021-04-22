package com.example.jieyue.admin.service;

import com.example.jieyue.common.entity.SysUser;
import com.example.jieyue.common.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {
    @Autowired
    SysUserMapper userMapper;

    /*
     * 获取用户信息
     */
    public List<SysUser> getUserList(int page, int num){
        return userMapper.findLimit((page-1)*num,num);
    }

    /*
     * 获取总页数
     */
    public int getAllPage(int num){
        return (int)Math.ceil((double)userMapper.userCount()/(double)num);
    }
}
