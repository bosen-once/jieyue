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
public class SysMt {
    private int id;
    private String name;
    private String email;
    private String password;
    private float ratio;
    private int state;
    private String header;
}
