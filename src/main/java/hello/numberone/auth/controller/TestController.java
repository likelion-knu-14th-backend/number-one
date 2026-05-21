package hello.numberone.auth.controller;

import hello.numberone.auth.common.SecurityUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/api/v1/test/me")
    public String me() {
        return "현재 로그인 사용자 : " + SecurityUtil.getCurrentEmail();
    }
}
