package com.test.dao;

import com.test.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liujiawang on 2019/5/1.
 */
public interface EvaluationDao extends JpaRepository<EvaluationEntity,Integer> {
    List<EvaluationEntity> findByPostIdOrderByEvaluationTimeDesc(int id);
    List<EvaluationEntity> findByUserIdOrderByEvaluationTimeDesc(int id);
}
