package com.ssg.my_wms2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {

    private String userid;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String company;
    private String role;
    private Timestamp created_at;
    private Timestamp updated_at;

}
