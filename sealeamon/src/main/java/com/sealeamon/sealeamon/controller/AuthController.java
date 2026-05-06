package com.sealeamon.sealeamon.controller;

import com.sealeamon.sealeamon.model.User;
import com.sealeamon.sealeamon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // 增加這行
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginReq) {
        return userRepository.findByUsernameAndPassword(loginReq.getUsername(), loginReq.getPassword())
            .map(user -> ResponseEntity.ok(Map.of("success", true, "message", "歡迎回來，老婆！")))
            .orElse(ResponseEntity.status(401).body(Map.of("success", false, "message", "帳號或密碼不對喔～")));
    }
}