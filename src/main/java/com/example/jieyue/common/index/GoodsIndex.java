package com.example.jieyue.common.index;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.math.BigDecimal;

/**
 * <p>goods表对应的es索引</p>
 * @author Bosen
 * @date 2021/8/8 15:23
 */
@Document(indexName = "jieyue_sys_goods")
@Data
public class GoodsIndex {
    @Id
    private int id;
    @Field
    private String name;
    @Field
    private String describe;
    @Field
    private String img;
    @Field
    private BigDecimal price;
    @Field
    private int state;
    @Field
    private int merchant;
    @Field
    private int stock;
}
