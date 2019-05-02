package com.test.dao;

import com.test.entity.CanteensEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liujiawang on 2019/4/29.
 */
public interface CanteensDao extends JpaRepository<CanteensEntity,Integer>{
    CanteensEntity queryByCanteenId(int id);
}
