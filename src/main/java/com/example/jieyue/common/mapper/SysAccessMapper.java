package com.example.jieyue.common.mapper;

import com.example.jieyue.common.entity.SysAccess;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysAccessMapper {
    List<SysAccess> findAll();

    int countByName(String name);

    int insert(String name,String url);

    int deleteById(int id);

    int updateStatus(int status,int id);

    int updateInfo(int id,String name,String url);
}
