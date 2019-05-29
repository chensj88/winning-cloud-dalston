package com.winning.devops.jwt.user.server.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author chensj
 * @date 2019-05-29 13:06
 */
public class BCryptPasswordEncoderUtils {

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    public static String encode(String password){
        return ENCODER.encode(password);
    }

    public static boolean mathes(CharSequence rawPassword,String encodePPassword){
        return ENCODER.matches(rawPassword,encodePPassword);
    }
}
