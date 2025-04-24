package com.ssg.my_wms2.mapper;

import com.ssg.my_wms2.dto.UserDTO;

public interface UserMapper {
    void insertUser(UserDTO dto);

    UserDTO login_M(UserDTO dto);

}
