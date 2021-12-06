package com.example.jieyue.merchant.service;

import com.example.jieyue.common.entity.SysUser;
import com.example.jieyue.common.mapper.SysUserMapper;
import com.example.jieyue.common.utils.GiteeImgBedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantUserService {
    @Autowired
    SysUserMapper userMapper;

    /**
     * <p>获取用户信息</p>
     */
    public List<SysUser> getUserList(int page, int num){
        List<SysUser> list = userMapper.findLimit((page-1)*num,num);
        for (SysUser user : list) {
            user.setHeader(GiteeImgBedUtils.PRE + user.getHeader());
        }
        return list;
    }

    /**
     * <p>获取总页数</p>
     */
    public int getAllPage(int num){
        return (int)Math.ceil((double)userMapper.userCount()/(double)num);
    }
}