package com.example.jieyue.user.service;

import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.common.mapper.SysGoodsMapper;
import com.example.jieyue.common.mapper.SysMtMapper;
import com.example.jieyue.common.utils.GiteeImgBedUtils;
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

    /**
     * <p>获取商户信息</p>
     */
    public SysMt getMerchantInfo(int merchantId){
        SysMt merchant = merchantMapper.findById(merchantId);
        merchant.setHeader(GiteeImgBedUtils.PRE + merchant.getHeader());
        return merchant;
    }

    /**
     * <p>获取商户商品信息</p>
     */
    public Map<Integer,SysGoods> getGoodsList(int merchantId, int page, int num){
        return common(goodsMapper.findByMtLimit(merchantId,(page-1)*num,page*num));
    }
    
    /**
     * <p>随机获取商品</p>
     */
    public Map<Integer,SysGoods> getRandGoodsMap(int merchant,int num){
        return common(goodsMapper.findMerchantRand(merchant,num));
    }

    public Map<Integer,SysGoods> common(List<SysGoods> list) {
        Map<Integer,SysGoods> map = new HashMap<>();
        for (int i = 0;i < list.size();i++) {
            list.get(i).setImg(GiteeImgBedUtils.PRE + list.get(i).getImg());
            map.put(i,list.get(i));
        }
        return map;
    }
    
    /**
     * <p>获取总页数</p>
     */
    public int getAllPage(int merchantId,int num){
        int all = goodsMapper.countByMerchant(merchantId);
        return (int)Math.ceil((double)all/(double)num);
    }

}

