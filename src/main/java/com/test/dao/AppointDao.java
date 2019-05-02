package com.test.dao;

import com.test.entity.AppointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liujiawang on 2019/4/30.
 */
public interface AppointDao extends JpaRepository<AppointEntity,Integer> {
    List<AppointEntity> findByUserIdOrderByAppointTimeDesc(int id);
    List<AppointEntity> findByAppointTimeGreaterThan(Timestamp t);
}
