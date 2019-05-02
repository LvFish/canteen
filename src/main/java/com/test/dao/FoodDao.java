package com.test.dao;

import com.test.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liujiawang on 2019/4/29.
 */
public interface FoodDao extends JpaRepository<FoodEntity,Integer>{
    FoodEntity queryByFoodId(int id);
}
