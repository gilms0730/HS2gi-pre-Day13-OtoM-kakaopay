package com.example.gms.user.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateUserRes {
    private Long id;
    private String email;
    private String name;
    private String image;
}
