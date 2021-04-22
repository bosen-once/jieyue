package com.example.jieyue.merchant.service;

import com.example.jieyue.common.mapper.SysCommentMapper;
import com.example.jieyue.common.mapper.SysOrderMapper;
import com.example.jieyue.common.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantHomeService {
    @Autowired
    SysCommentMapper commentMapper;
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    SysOrderMapper orderMapper;

    public int getCommandCount(int merchant){
        return commentMapper.getAllCountByMt(merchant);
    }

    public int getUserCount(int merchant){
        return userMapper.userCount();
    }

    public int getOrderCount(int merchant){
        return orderMapper.payCountByMt(merchant);
    }

}
