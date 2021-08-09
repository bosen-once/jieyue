package com.example.jieyue.common.mapper;

import com.example.jieyue.common.entity.SysMt;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMtMapper {
    int insert(String name,String email,String password,float ratio,int state);

    SysMt findByEmail(String email);

    SysMt findById(int id);

    int count();

    List<SysMt> findPage(int curRow,int pageSize);

    List<SysMt> findAll();

    int deleteById(int id);

    int updateRatio(int id,float ratio);

    int updateState(String email,int state);

    int updateHeader(int id,String header);

    int update(String name,String password,String email);

    int updateName(String name);
}
