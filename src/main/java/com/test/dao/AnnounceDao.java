package com.test.dao;

import com.test.entity.AnnounceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liujiawang on 2019/4/30.
 */
public interface AnnounceDao extends JpaRepository<AnnounceEntity,Integer>{
    AnnounceEntity findByAnnounceDetail(String detail);
}
