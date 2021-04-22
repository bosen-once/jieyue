package com.example.jieyue.common.mapper;

import com.example.jieyue.common.entity.SysRoleAccess;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleAccessMapper {
    List<SysRoleAccess> findAll();

    List<SysRoleAccess> findByRoleId(int roleId);

    int insert(int roleId,int accessId);

    int deleteById(int id);

    int deleteByRoleId(int roleId);

    int deleteByAccessId(int accessId);

    int updateStatus(int status,int id);
}
