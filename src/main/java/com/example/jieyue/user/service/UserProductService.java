package com.example.jieyue.user.service;

import com.example.jieyue.common.entity.SysComment;
import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.entity.SysUser;
import com.example.jieyue.common.mapper.SysCommentMapper;
import com.example.jieyue.common.mapper.SysGoodsMapper;
import com.example.jieyue.common.mapper.SysUserMapper;
import com.example.jieyue.common.utils.GiteeImgBedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserProductService {
    @Autowired
    SysGoodsMapper goodsMapper;
    @Autowired
    SysCommentMapper commentMapper;
    @Autowired
    SysUserMapper userMapper;
    
    /**
     * <p>整合用户信息和评论信息</p>
     */
    public List<Map> getCommentList(int goods, int page, int num){
        List<SysComment> commentInfo = getCommentInfo(goods,page,num);
        List<Map> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (SysComment comment : commentInfo) {
            Map<String,String> commentMap = new HashMap<>();
            SysUser user = getUserInfo(comment.getUser());
            commentMap.put("userHeader",GiteeImgBedUtils.PRE + user.getHeader());
            commentMap.put("userId",user.getId()+"");
            commentMap.put("commentId",comment.getId()+"");
            commentMap.put("userName",user.getUsername());
            commentMap.put("createTime",sdf.format(new Date(comment.getCreateTime())));
            commentMap.put("context",comment.getContext());

            list.add(commentMap);
        }
        return list;
    }
    
    /**
     * <p>获取当前商品总评论页数</p>
     */
    public int getAllCountPage(int goods,int num){
        int count = commentMapper.getAllCountByGoods(goods);
        return (int)Math.ceil((double)count/(double)num);
    }
    
    /**
     * <p>通过id值获取商品对象</p>
     */
    public SysGoods getGoods(int id){
        SysGoods goods = goodsMapper.findById(id);
        goods.setImg(GiteeImgBedUtils.PRE + goods.getImg());
        return goods;
    }

    /**
     * <p>获取商品评论列表</p>
     * @param goods 商品id
     * @param page 评论的页数
     * @param num 一页评论的条数
     */
    public List<SysComment> getCommentInfo(int goods, int page, int num){
        return commentMapper.findByGoodsLimit(goods,(page-1)*num,page*num);
    }
    
    /**
     * <p>获取评论对应的商户信息</p>
     */
    public SysUser getUserInfo(int userId){
        return userMapper.selectById(userId);
    }
    
    /**
     * <p>添加商品评论</p>
     * @return
     *-1 添加失败
     * 1 添加成功
     */
    public int addComment(int goodsId,int merchant,String context,HttpServletRequest request){
        // 获取发表评论的用户信息
        SysUser user = (SysUser)request.getSession().getAttribute("user");
        // 生成时间
        long time = System.currentTimeMillis();
        int sql = commentMapper.insert(user.getId(),goodsId,merchant,context,time);
        if (sql == 1){
            return 1;
        }
        return -1;
    }
}
