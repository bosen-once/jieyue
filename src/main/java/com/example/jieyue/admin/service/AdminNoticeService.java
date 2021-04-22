package com.example.jieyue.admin.service;

import com.example.jieyue.common.entity.SysAdmin;
import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.common.entity.SysUser;
import com.example.jieyue.common.mapper.SysAdminMapper;
import com.example.jieyue.common.mapper.SysMtMapper;
import com.example.jieyue.common.mapper.SysNoticeMapper;
import com.example.jieyue.common.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminNoticeService {
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    SysMtMapper merchantMapper;
    @Autowired
    SysAdminMapper adminMapper;
    @Autowired
    SysNoticeMapper noticeMapper;
    @Autowired
    RedisTemplate redisTemplate;

    /*
     * 将要发送的消息存入redis消息队列
     */
    public void send(String title,String context,int type){
        Map<String,String> map = new HashMap<>();
        map.put("title",title);
        map.put("context",context);
        map.put("type",type+"");
        switch (type){
            case 0:
                // 获取信息
                List<SysAdmin> adminList = adminMapper.findAll();
                for (SysAdmin sysAdmin : adminList) {
                    long createTime = System.currentTimeMillis();
                    map.put("createTime",createTime+"");
                    map.put("receive",sysAdmin.getId()+"");
                    redisTemplate.opsForList().leftPush("notice",map);
                }
                break;
            case 1:
                // 获取信息
                List<SysMt> merchantList = merchantMapper.findAll();
                for (SysMt sysMt : merchantList) {
                    long createTime = System.currentTimeMillis();
                    map.put("createTime",createTime+"");
                    map.put("receive",sysMt.getId()+"");
                    redisTemplate.opsForList().leftPush("notice",map);
                }
                break;
            case 2:
                // 获取信息
                List<SysUser> userList = userMapper.findAll();
                for (SysUser sysUser : userList) {
                    long createTime = System.currentTimeMillis();
                    map.put("createTime",createTime+"");
                    map.put("receive",sysUser.getId()+"");
                    redisTemplate.opsForList().leftPush("notice",map);
                }
                break;
        }
    }
}
