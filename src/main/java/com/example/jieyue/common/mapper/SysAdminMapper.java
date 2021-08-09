package com.example.jieyue.common.mapper;

import com.example.jieyue.common.entity.SysAdmin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysAdminMapper {
    List<SysAdmin> findAll();

    int insert(@Param("name") String name, @Param("password") String password,
               @Param("email") String email, @Param("mark") int mark);

    SysAdmin selectByEmail(String email);

    SysAdmin getAdminInfo(String email);

    int deleteById(int id);

    int updateMark(int mark,String email);

    int updateMarkById(int id,int mark);

    int update(String name,String password,String email);
}
