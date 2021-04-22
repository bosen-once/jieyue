package com.example.jieyue.common.mapper;

import com.example.jieyue.common.entity.SysNotice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysNoticeMapper {
    List<SysNotice> findByReceiveTypeLimit(int receive,int type,int preNum,int sufNum);

    int deleteById(int id);

    int insert(String title,String context,int type,int receive,long createTime);
}
