package com.springcloud.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2021/2/18 21:43:00
 * @description TODO
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping
    public String findAll() {
        return "查询产品列表成功！";
    }

    /*@Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Bean
    public PasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping("/testPassword")
    public void testPassword() {
        String password = bCryptPasswordEncoder.encode("admin");
        return;
    }*/
}
