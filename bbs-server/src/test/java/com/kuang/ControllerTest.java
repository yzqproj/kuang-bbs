package com.kuang;

import com.kuang.model.entity.User;
import com.kuang.utils.UserUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yanni
 * @date time 2021/11/22 16:42
 * @modified By:
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ControllerTest {  @Test
void getUByUid(){
    User u=  UserUtil.getUserByUserCode("4a6c46eda2254985818e4c8d08a9b879");
    System.out.println(u);
}
}
