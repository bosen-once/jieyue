package com.example.jieyue.common.mapper;

import com.example.jieyue.common.entity.SysAdminRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysAdminRoleMapper {
    List<SysAdminRole> findAll();

    SysAdminRole findByAdminId(int adminId);

    int insert(int adminId,int roleId);

    int deleteById(int id);

    int deleteByAdminId(int adminId);

    int deleteByRoleId(int roleId);

    int updateStatus(int status,int id);
}
