package com.example.gms.user.service;

import com.example.gms.user.model.EmailVerify;
import com.example.gms.user.repository.EmailVerifyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailVerifyService {
    private  final EmailVerifyRepository emailVerifyRepository;

    public boolean verify(String email, String uuid){

        Optional<EmailVerify> result = emailVerifyRepository.findByEmail(email);
        if(result.isPresent()){
            EmailVerify emailVerify = result.get();
            if(emailVerify.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public  void create (String email, String uuid){
        EmailVerify emailVerify = EmailVerify.builder()
                .email(email)
                .uuid(uuid)
                .build();
        emailVerifyRepository.save(emailVerify);
    }
}

