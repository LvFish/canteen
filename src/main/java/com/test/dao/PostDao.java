package com.test.dao;

import com.test.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liujiawang on 2019/5/1.
 */
public interface PostDao extends JpaRepository<PostEntity,Integer> {
    PostEntity findByPostId(int id);
    List<PostEntity> findByUserIdOrderByPostTimeDesc(int id);
}
