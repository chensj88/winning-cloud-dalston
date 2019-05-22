package com.winning.devops.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.test
 * @date: 2019-05-22 22:01
 */
public class EncoderTest {

    public static void main(String[] args){
        String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
        System.out.println(finalSecret);
    }
}
