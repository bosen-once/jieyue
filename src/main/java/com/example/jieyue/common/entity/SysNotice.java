package com.example.jieyue.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>数据库表的映射类</p>
 * @author Bosen
 * @date 2021/8/9 22:46
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysNotice implements Serializable {
    private int id;
    private int type;
    private String title;
    private String context;
    private int receive;
    private long createTime;
}
