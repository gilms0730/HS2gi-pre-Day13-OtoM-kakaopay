package com.example.gms.user.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostCreateUserReq {
    private String email;
    private String password;
    private String name;
    private MultipartFile image;
}
