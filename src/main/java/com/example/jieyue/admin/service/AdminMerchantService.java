package com.example.jieyue.admin.service;

import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.common.mapper.SysMtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMerchantService {
    @Autowired
    SysMtMapper mtMapper;
    /**
     * <p>获取用户的信息</p>
     *
     * @param curPage 当前页数
     * @param pageSize 一页的条数
     */
    public List<SysMt> getMtInfo(int curPage,int pageSize){
        int curRow = (curPage-1)*pageSize;
        return mtMapper.findPage(curRow,pageSize);
    }
    
    /*
     * 删除商户
     */
    public int deleteMerchant(int merchantId){
        if (mtMapper.deleteById(merchantId)==1){
            return 1;
        }
        return -1;
    }

    /*
     * 修改用户状态
     */
    public int updateMerchantState(String email,int state){
        if (mtMapper.updateState(email,state)==1){
            return 1;
        }
        return -1;
    }
    
    /*
     * 获取商户列表总页数
     */
    public int getMtPage(int pageSize){
        if (mtMapper.count()==0){
            return 1;
        }else{
            return (int)Math.ceil((double)mtMapper.count()/pageSize);
        }
    }

    public int updateRatio(float ratio,int id){
        return 1;
    }
}
