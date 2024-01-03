package com.example.gms.user.controller;

import com.example.gms.user.model.dto.PostCreateUserReq;
import com.example.gms.user.model.User;
import com.example.gms.user.model.dto.PostCreateUserRes;
import com.example.gms.user.service.EmailVerifyService;
import com.example.gms.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private final JavaMailSender emailSender;
    private final EmailVerifyService emailVerifyService;

    public UserController(UserService userService, JavaMailSender emailSender, EmailVerifyService emailVerifyService) {
        this.userService = userService;
        this.emailSender = emailSender;
        this.emailVerifyService = emailVerifyService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signupUser (PostCreateUserReq request){
        PostCreateUserRes response = userService.signupUser(request);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getEmail());
        message.setSubject("[지엠에스] 이메일 인증");
        String uuid = UUID.randomUUID().toString();
        message.setText("test");
        emailSender.send(message);
        emailVerifyService.create(request.getEmail(), uuid);
        return ResponseEntity.ok().body(response);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public  ResponseEntity loginUser (Long id){
        userService.loginUser(id);
        return ResponseEntity.ok().body("로그인 완료");
    }
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public  ResponseEntity updateUser (String email) {
        userService.updateUser(email);
        return ResponseEntity.ok().body("회원 수정 완료");
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public  ResponseEntity deleteUser(long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("회원 탈퇴 완료");
    }
    @RequestMapping(method = RequestMethod.GET, value = "/verify")
    public ResponseEntity verify(String email, String uuid) {
        if(emailVerifyService.verify(email, uuid)) {
            userService.updateUser(email);
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.ok().body("error");
    }
}
