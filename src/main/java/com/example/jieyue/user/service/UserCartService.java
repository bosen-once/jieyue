package com.example.jieyue.user.service;

import com.example.jieyue.common.entity.SysCart;
import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.common.entity.SysUser;
import com.example.jieyue.common.mapper.SysCartMapper;
import com.example.jieyue.common.mapper.SysGoodsMapper;
import com.example.jieyue.common.mapper.SysMtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserCartService {
    @Autowired
    SysCartMapper cartMapper;
    @Autowired
    SysGoodsMapper goodsMapper;
    @Autowired
    SysMtMapper merchantMapper;

    /**
     * <p>添加至购物车</p>
     * @return
     *-1 添加至购物车失败
     * 1 添加至购物车成功
     * 2 用户在此前已添加至购物车
     * @author Bosen
     * 2020/11/29 10:44
     */
    public int addCart(int id, int num, HttpServletRequest request){
        // 获取用户信息
        SysUser user = (SysUser)request.getSession().getAttribute("user");
        // 判断用户是否已将此商品添加至购物车
        int count = cartMapper.countByUserIdAndGoodsId(user.getId(),id);
        if (count == 0){
            int sql = cartMapper.insert(user.getId(),id,num);
            if (sql == 1){
                return 1;
            }
        }else{
            return 2;
        }
        return -1;
    }
    
    /*
     * 整合购物车完整信息
     */
    public List<Map> getCartList(HttpServletRequest request){
        // 获取用户信息
        SysUser user = (SysUser) request.getSession().getAttribute("user");
        List<Map> list = new ArrayList<>();
        List<SysCart> cartList = cartMapper.findByUserIdLimit(user.getId());
        for (SysCart cart : cartList) {
            SysGoods goods = goodsMapper.findById(cart.getGoodsId());
            SysMt merchant = merchantMapper.findById(goods.getMerchant());
            Map<String,String> map = new HashMap<>();
            map.put("goodsImg",goods.getImg());
            map.put("cartId",cart.getId()+"");
            map.put("goodsId",goods.getId()+"");
            map.put("goodsPrice",goods.getPrice().toPlainString());
            map.put("goodsName",goods.getName());
            map.put("goodsNum",cart.getGoodsNum()+"");
            map.put("goodsStock",goods.getStock()+"");
            map.put("goodsState",goods.getState()+"");
            map.put("goodsMerchant",merchant.getName());

            list.add(map);
        }
        return list;
    }

}
