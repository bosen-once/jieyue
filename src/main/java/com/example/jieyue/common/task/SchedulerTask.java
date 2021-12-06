package com.example.jieyue.common.task;

import com.example.jieyue.common.entity.SysOrder;
import com.example.jieyue.common.mapper.SysGoodsMapper;
import com.example.jieyue.common.mapper.SysNoticeMapper;
import com.example.jieyue.common.mapper.SysOrderMapper;
import com.example.jieyue.common.service.MailService;
import com.example.jieyue.common.utils.GiteeImgBedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

/**
 * <p>定时任务执行类</p>
 * @author Bosen
 * 2020/11/13 13:41
 */
@Component
public class SchedulerTask {
    @Autowired
    SysOrderMapper orderMapper;
    @Autowired
    SysGoodsMapper goodsMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    SysNoticeMapper noticeMapper;
    @Autowired
    MailService mailService;

    /**
     * <p>每分钟执行一次，检查是否有过期的订单，若过期将其删除，并恢复库存</p>
     */
    @Scheduled(cron="0 0/1 * * * ?")
    @Transactional
    public void delOverOrder(){
        // 订单过期时间，两小时
        long time = 2*60*60*1000;
        List<SysOrder> list = orderMapper.findByState(0);
        for (SysOrder sysOrder : list) {
            if (System.currentTimeMillis() > sysOrder.getCreateTime() + time
                    && sysOrder.getOrderState() == 0){
                if (orderMapper.deleteById(sysOrder.getId()) != 1 ||
                        goodsMapper.addStock(sysOrder.getGoodsId(),sysOrder.getGoodsNum()) != 1){
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
            }
        }
    }

    /**
     * <p>每分钟执行一次，对已创建了两小时订单的二维码进行删除</p>
     */
    @Scheduled(cron="0 0/1 * * * ?")
    public void delQRCode(){
        // 订单过期时间，两小时
        long time = 2*60*60*1000;
        List<SysOrder> list = orderMapper.findAll();
        for (SysOrder sysOrder : list) {
            if (System.currentTimeMillis() > sysOrder.getCreateTime() + time
                    && sysOrder.getOrderState() == 0){
                GiteeImgBedUtils.delete(sysOrder.getPayCodeUrl());
            }
        }
    }

    /**
     * <p>每分钟执行一次，对redis消息队列的消息执行发送</p>
     */
    @Scheduled(cron="0 0/1 * * * ?")
    public void sendNotice(){
        while (redisTemplate.opsForList().size("notice")!=0){
            Map<String,String> map = (Map<String,String>)redisTemplate.opsForList().rightPop("notice");
            String title = map.get("title");
            String context = map.get("context");
            int type = Integer.valueOf(map.get("type"));
            int receive = Integer.valueOf(map.get("receive"));
            long createTime = Long.valueOf(map.get("createTime"));

            noticeMapper.insert(title,context,type,receive,createTime);
        }
    }

    /**
     * <p>每分钟执行一次，邮箱发送队列的执行发送</p>
     */
    @Scheduled(cron="0 0/1 * * * ?")
    public void sendEmail(){
        while (redisTemplate.opsForList().size("email")!=0){
            Map<String,String> map = (Map<String,String>)redisTemplate.opsForList().rightPop("email");
            mailService.sendHtmlMail(map.get("email"),map.get("title"),map.get("context"));
        }
    }
}
