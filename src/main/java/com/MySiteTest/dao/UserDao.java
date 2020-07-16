package com.MySiteTest.dao;

import com.MySiteTest.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDao {
    User selectUserByName(String name);
}
