package com.example.jieyue.user.service;

import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.mapper.SysGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSearchService {
    @Autowired
    SysGoodsMapper goodsMapper;
    /*
     * 通过关键字模糊查找商品
     */
    public List<SysGoods> searchGoods(String keyword){
        return goodsMapper.search(keyword);
    }
}
