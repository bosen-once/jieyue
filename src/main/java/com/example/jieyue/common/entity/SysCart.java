package com.example.jieyue.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>数据库表的映射类</p>
 * @author Bosen
 * @date 2021/8/9 22:46
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysCart {
    private int id;
    private int goodsId;
    private int userId;
    private int goodsNum;
}
