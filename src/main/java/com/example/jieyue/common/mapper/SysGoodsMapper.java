package com.example.jieyue.common.mapper;

import com.example.jieyue.common.entity.SysGoods;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SysGoodsMapper {
    SysGoods findById(int id);
    List<SysGoods> findByMt(int merchant);
    List<SysGoods> findByMtLimit(int merchant,int preNum,int sufNum);
    List<SysGoods> findAll();
    List<SysGoods> findLimitByMt(int merchant,int preNum,int sufNum);
    List<SysGoods> findAllEsc(int num);
    List<SysGoods> findRand(int num);
    List<SysGoods> findMerchantRand(int merchant,int num);
    List<SysGoods> findAllDesc(int num);
    List<SysGoods> search(String keyword);
    int goodsCount(int merchant);
    int allGoodsCount();
    int countByMerchant(int merchant);
    int deleteById(int id);
    int addStock(int id,int stock);
    int delStock(int id,int stock);
    int updateState(int id,int state);
    int updateGoods1(String name,String describe,BigDecimal price,int merchant,int stock,int id);
    int updateGoods2(String name,String describe,BigDecimal price,int merchant,int stock,int id,String imgUrl);
    int insert1(String name,String describe,BigDecimal price,int merchant,int stock);
    int insert2(String name, String describe, BigDecimal price, int merchant,int stock, String img);
}
