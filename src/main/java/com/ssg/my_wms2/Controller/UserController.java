package com.ssg.my_wms2.Controller;

import com.ssg.my_wms2.Service.UserService;
import com.ssg.my_wms2.dto.UserDTO;
import com.ssg.my_wms2.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class UserController {
    private final UserService userService;

    // 회원가입 폼
    @GetMapping("/register")
    public String showRegisterForm() {
        return "todo/register"; // -> /WEB-INF/views/todo/register.jsp
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String processRegister(UserDTO dto, Model model) {
        log.info("회원가입 요청: {}", dto);
        boolean result = userService.register(dto);

        if (result) {
            return "redirect:/index.jsp";
        } else {
            model.addAttribute("error", "회원가입에 실패했습니다.");
            return "todo/register";
        }
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login_C(UserDTO dto, HttpSession session, Model model) {
        UserDTO loginUser = userService.login_S(dto);

        if (loginUser != null) {
            session.setAttribute("user", loginUser);
            session.setAttribute("login_id", loginUser.getUserid());
            return "redirect:/todo/main";
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "redirect:/index.jsp"; // 로그인 실패 시 다시 로그인 폼으로
        }
    }

    // 로그인 성공 후 이동하는 메인 페이지
    @GetMapping("/main")
    public String showMainPage() {
        return "todo/main"; // -> /WEB-INF/views/todo/main.jsp
    }



}
