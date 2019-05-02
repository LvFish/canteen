package com.test.dao;

import com.test.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liujiawang on 2019/5/1.
 */
public interface UserDao  extends JpaRepository<UserEntity,Integer>{
    UserEntity findByUserOpenId(String openId);
    UserEntity findByUserId(int id);
}
