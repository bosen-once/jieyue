package com.example.jieyue.admin.service;

import com.example.jieyue.common.mapper.SysRoleAccessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class AdminRbacService {

    @Autowired
    SysRoleAccessMapper roleAccessMapper;

    /**
     * <p>修改角色的权限</p>
     */
    @Transactional
    public boolean setRoleAccess(int role,int[] ids){
        // 删除角色原权限
        roleAccessMapper.deleteByRoleId(role);
        for (int id : ids) {
            if (roleAccessMapper.insert(role,id)!=1){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
        }
        return true;
    }
}
