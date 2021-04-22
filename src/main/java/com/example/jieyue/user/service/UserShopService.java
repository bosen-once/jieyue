package com.example.jieyue.user.service;

import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.common.mapper.SysGoodsMapper;
import com.example.jieyue.common.mapper.SysMtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserShopService {
    @Autowired
    SysMtMapper merchantMapper;
    @Autowired
    SysGoodsMapper goodsMapper;

    /*
     * 获取商户信息
     */
    public SysMt getMerchantInfo(int merchantId){
        return merchantMapper.findById(merchantId);
    }

    /*
     * 获取商户商品信息
     */
    public Map<Integer,SysGoods> getGoodsList(int merchantId, int page, int num){
        List<SysGoods> list = goodsMapper.findByMtLimit(merchantId,(page-1)*num,page*num);
        Map<Integer,SysGoods> map = new HashMap<>();
        for (int i = 0;i < list.size();i++){
            map.put(i,list.get(i));
        }
        return map;
    }
    
    /*
     * 随机获取商品
     */
    public Map<Integer,SysGoods> getRandGoodsMap(int merchant,int num){
        List<SysGoods> list = goodsMapper.findMerchantRand(merchant,num);
        Map<Integer,SysGoods> map = new HashMap<>();
        for (int i = 0;i < list.size();i++) {
            map.put(i,list.get(i));
        }
        return map;
    }
    
    /*
     * 获取总页数
     */
    public int getAllPage(int merchantId,int num){
        int all = goodsMapper.countByMerchant(merchantId);
        return (int)Math.ceil((double)all/(double)num);
    }

}

