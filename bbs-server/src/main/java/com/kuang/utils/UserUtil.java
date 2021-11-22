package com.kuang.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuang.model.entity.User;

public class UserUtil {
    public static User getUserByUserCode(String userCode) {

         User u = new  User();
        u.setUserId(userCode);

        return u.selectOne(new QueryWrapper<>(u));

    }
    public static  User getUserByName(String name){
        User u=new User();
        u.setUsername(name);
        return u.selectOne(new QueryWrapper<>(u));
    }
}
