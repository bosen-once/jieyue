package com.example.jieyue.common.mapper;

import com.example.jieyue.common.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper {
    SysUser selectById(int id);

    List<SysUser> findLimit(int preNum,int sufNum);

    int userCount();

    List<SysUser> findAll();

    List<Integer> getAllId();

    int insert(@Param("username") String username,@Param("password") String password,
               @Param("email") String email,@Param("mark") int mark);

    SysUser selectByEmail(String email);

    SysUser getUserInfo(String email);

    int updateMark(int mark,String email);

    int update(String username,String password,String email);

    int updateById(String username,String password,String header,String email,int id);
}
