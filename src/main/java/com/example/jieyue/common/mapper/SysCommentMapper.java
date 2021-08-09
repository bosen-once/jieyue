package com.example.jieyue.common.mapper;

import com.example.jieyue.common.entity.SysComment;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysCommentMapper {
    SysComment findById(int id);

    int getAllCountByGoods(int id);

    int getAllCountByMt(int merchant);

    List<SysComment> findByGoodsLimit(int goods,int preNum,int sufNum);

    int insert(int user,int goods,int merchant,String context,long createTime);

    int deleteById(int id);
}
