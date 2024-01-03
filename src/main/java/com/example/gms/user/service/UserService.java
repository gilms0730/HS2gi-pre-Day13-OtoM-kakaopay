package com.example.gms.user.service;

import com.example.gms.order.model.Order;
import com.example.gms.user.model.dto.PostCreateUserReq;
import com.example.gms.user.model.User;
import com.example.gms.user.model.dto.PostCreateUserRes;
import com.example.gms.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public PostCreateUserRes signupUser (PostCreateUserReq postCreateUserReq){
        User user = User.builder()
                .email(postCreateUserReq.getEmail())
                .password(postCreateUserReq.getPassword())
                .name(postCreateUserReq.getName())
                .isValid(false)
                .build();
        User result = userRepository.save(user);

        PostCreateUserRes response = PostCreateUserRes.builder()
                .id(result.getId())
                .email(result.getEmail())
                .name(result.getName())
                .build();

        return response;
    }
    public void loginUser (Long id) {
        userRepository.findById(id);
    }

    public void updateUser (String email) {
        Optional<User> result = userRepository.findByEmail(email);
        if(result.isPresent()) {
            User user = result.get();
            user.setValid(true);
            userRepository.save(user);
        }
    }
    public void deleteUser(Long id){
        userRepository.delete(User.builder()
                .id(id)
                .build());
    }
}
