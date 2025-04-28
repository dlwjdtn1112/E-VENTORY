package com.ssg.my_wms2.Service;

import com.ssg.my_wms2.dto.UserDTO;
import com.ssg.my_wms2.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor

public class UserService {


    private final UserMapper userMapper;
    private final ModelMapper modelMapper;

//    public boolean register(UserDTO dto) {
//        try {
//            userMapper.insertUser(dto);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
public boolean register(UserDTO dto) {
    try {
        // 1. 아이디 중복 체크
        UserDTO existingUser = userMapper.findByUserid(dto.getUserid());
        if (existingUser != null) {
            log.warn("이미 존재하는 아이디: {}", dto.getUserid());
            return false; // 중복 -> 실패 처리
        }

        // 2. 중복 아니면 insert
        userMapper.insertUser(dto);
        log.info("회원가입 성공: {}", dto.getUserid());
        return true;
    } catch (Exception e) {
        log.error("회원가입 중 오류 발생", e);
        return false;
    }
}



    public UserDTO login_S(UserDTO dto) {
        return userMapper.login_M(dto);  // 아이디와 비밀번호 비교해서 결과 반환
    }



}
