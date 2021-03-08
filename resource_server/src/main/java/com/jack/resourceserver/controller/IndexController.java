package com.jack.resourceserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class IndexController {

    @RequestMapping("user")
    public String user() {
        return "user";
    }

    //测试接口
    @RequestMapping("admin")
    @PreAuthorize("hasAnyAuthority('admin')")
    public String admin() {
        return "admin";
    }

    @RequestMapping("me")
    public Principal me(Principal principal) {
        return principal;
    }
}
