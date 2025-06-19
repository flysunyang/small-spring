package org.springframework.test.service;

import java.util.Random;

public class UserServiceImpl implements UserService {
    
    @Override
    public String getUserInfo() {
        return "query user, please wait...";
    }

    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }
    
}
