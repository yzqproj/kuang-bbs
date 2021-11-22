package com.kuang;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author yanni
 * @date time 2021/11/22 12:41
 * @modified By:
 */
public class EncodePass {
    @Test
    void getCode(){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String b=bCryptPasswordEncoder.encode("123456");
        System.out.println(b);

    }
}
