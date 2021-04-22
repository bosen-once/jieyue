package com.example.jieyue.common.mapper;

import com.example.jieyue.common.entity.SysMtUi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMtUiMapper {
    SysMtUi findByMark(int width, int height, int merchant);
    SysMtUi findById(int id);
    List<SysMtUi> findLimit(int width, int height, int num);
    int updateUrl(String url, int width, int height,int merchant);
    int insert(String url, int width, int height,int merchant);
    int deleteByMark(int width, int height,int merchant);
    int deleteById(int id);
}
