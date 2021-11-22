package com.kuang.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuang.model.entity.User;

public class UserUtil {
    public static User getUserByUserCode(int userCode) {

         User u = new  User();
        u.setId(userCode);

        return u.selectOne(new QueryWrapper<>(u));

    }
}
