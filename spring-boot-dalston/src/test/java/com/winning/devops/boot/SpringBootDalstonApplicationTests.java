package com.winning.devops.boot;

import com.winning.devops.boot.web.dao.UserRepository;
import com.winning.devops.boot.web.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chensj
 * @title 测试类
 * @package com.winning.devops.boot
 * @date: 2019-04-20 11:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDalstonApplicationTests {
    @Test
    public void contextLoads() {
    }

    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertUserTest(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        userRepository.save(user);
    }
}
