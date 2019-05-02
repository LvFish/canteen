package com.test.dao;

import com.test.entity.FoodtypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liujiawang on 2019/4/29.
 */
public interface FoodTypeDao extends JpaRepository<FoodtypeEntity,Integer> {
    FoodtypeEntity queryByTypeId(int id);
}
