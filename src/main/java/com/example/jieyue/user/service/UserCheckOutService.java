package com.example.jieyue.user.service;

import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.mapper.SysGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserCheckOutService {
    @Autowired
    SysGoodsMapper goodsMapper;

    /**
     * <p>获取商品信息</p>
     */
    public Map<SysGoods,Integer> getGoodsInfo(String ids){
        Map<SysGoods,Integer> map = new HashMap<>();
        Map<Integer,Integer> idsMap = getIdsMap(ids);
        for (Integer id : idsMap.keySet()) {
            map.put(goodsMapper.findById(id),idsMap.get(id));
        }
        return map;
    }
    
    /**
     * <p>获取总金额</p>
     */
    public BigDecimal getGoodsNumPrice(Map<SysGoods,Integer> goodsMap){
        BigDecimal allPrice = new BigDecimal(0.00);
        for (SysGoods goods : goodsMap.keySet()) {
            allPrice = allPrice.add(goods.getPrice().multiply(new BigDecimal(goodsMap.get(goods))));
        }
        return allPrice;
    }

    /**
     * <p>将前端发送的ids字符串切割为哈希表</p>
     */
    public Map<Integer,Integer> getIdsMap(String ids){
        String[] idsAndNumArray = ids.split(",");
        Map<Integer, Integer> map = new HashMap<>();
        for (String idsAndNumA : idsAndNumArray) {
            String[] idsAndNum = idsAndNumA.split(":");
            int id = Integer.valueOf(idsAndNum[0]);
            int num = Integer.valueOf(idsAndNum[1]);
            map.put(id,num);
        }
        return map;
    }

    /**
     * <p>获取对应的cart id</p>
     */
    public List<Integer> getCartIds(String ids){
        String[] idsAndNumArray = ids.split(",");
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i < idsAndNumArray.length;i++) {
            String[] idsAndNum = idsAndNumArray[i].split(":");
            int cart = Integer.valueOf(idsAndNum[2]);
            list.add(cart);
        }
        return list;
    }
}
