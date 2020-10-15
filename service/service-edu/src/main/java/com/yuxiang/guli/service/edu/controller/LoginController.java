package com.yuxiang.guli.service.edu.controller;

import com.yuxiang.guli.common.base.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/edu/user")
@Slf4j
public class LoginController {


    @PostMapping("/login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("/info")
    public R info() {

        return R.ok()
                .data("name", "yuxiang")
                .data("roles", "[admin,user]")
                .data("avatar", "https://video-guli.oss-cn-beijing.aliyuncs.com/teacher/yuxin.png");
    }

    @PostMapping("/logout")
    public R logout() {

        return R.ok();
    }
}

