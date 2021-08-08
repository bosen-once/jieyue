package com.example.jieyue.user.service;

import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.index.GoodsIndex;
import com.example.jieyue.common.mapper.SysGoodsMapper;
import com.example.jieyue.common.repository.GoodsIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSearchService {
    @Autowired
    SysGoodsMapper goodsMapper;

    @Autowired
    GoodsIndexRepository goodsIndexRepository;

    /**
     * <p>mysql通过关键字模糊查找商品</p>
     */
    public List<SysGoods> searchGoods(String keyword){
        return goodsMapper.search(keyword);
    }

    /**
     * <p>es通过关键字模糊查找商品</p>
     */
    public List<GoodsIndex> esSearchGoods(String keyword) {
        return goodsIndexRepository.findByNameOrDescribe(keyword, keyword);
    }
}
