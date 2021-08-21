package com.example.jieyue.admin.listener;

import com.example.jieyue.common.entity.SysNotice;
import com.example.jieyue.common.mapper.SysNoticeMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Map;

/**
 * <p>群发消息队列监听类</p>
 * @author Bosen
 * @date 2021/8/8 23:30
 */
// @Component
@Slf4j
public class NoticeListener {
    @Autowired
    SysNoticeMapper noticeMapper;

    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(name = "notice-exchange"),
                    value = @Queue(value = "notice-queue"),
                    key = "notice.*"
            )
    )
    @RabbitHandler
    public void sendNotice(
            @Headers Map<String, Object> headers,
            @Payload SysNotice notice,
            Channel channel) throws IOException {
        if (insertToMysql(notice) != 1) {
            return;
        }
        // 手动确认消费
        channel.basicAck((long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
        log.info("Rabbit订阅：群发通知入库成功！");
    }

    /**
     * <p>将通知消息插入进mysql</p>
     */
    @RequestMapping("/insert/to/mysql")
    public int insertToMysql(SysNotice notice) {

        String title = notice.getTitle();
        String context = notice.getContext();
        int type = notice.getType();
        int receive = notice.getReceive();
        long createTime = notice.getCreateTime();

        return noticeMapper.insert(title, context, type, receive, createTime);
    }
}
