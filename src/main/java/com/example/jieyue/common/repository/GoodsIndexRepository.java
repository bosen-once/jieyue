package com.example.jieyue.common.repository;

import com.example.jieyue.common.index.GoodsIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>goods索引操作对象</p>
 * @author Bosen
 * @date 2021/8/8 15:28
 */
@Repository
public interface GoodsIndexRepository extends ElasticsearchRepository<GoodsIndex, Integer> {
    /**
     * <p>通过名字或描述匹配</p>
     * @param keyword1 String
     * @param keyword2 String
     * @return List<GoodsIndex>
     */
    List<GoodsIndex> findByNameOrDescribe(String keyword1, String keyword2);
}
