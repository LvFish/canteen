package com.test.dao;

import com.test.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liujiawang on 2019/4/29.
 */
public interface ManagerDao extends JpaRepository<ManagerEntity, Integer> {
    ManagerEntity findByManagerName(String managerName);
}
