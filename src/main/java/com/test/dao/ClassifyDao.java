package com.test.dao;

import com.test.entity.ClassifyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liujiawang on 2019/4/30.
 */
public interface ClassifyDao extends JpaRepository<ClassifyEntity,Integer>{
    List<ClassifyEntity> queryByClassifyType(int id);
    ClassifyEntity findByFoodId(int id);
    ClassifyEntity findByClassifyId(int id);
}
