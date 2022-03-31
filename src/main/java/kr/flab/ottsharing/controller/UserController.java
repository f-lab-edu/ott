package kr.flab.ottsharing.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import kr.flab.ottsharing.protocol.MyPageUpdateResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.flab.ottsharing.entity.User;
import kr.flab.ottsharing.protocol.MyInfo;
import kr.flab.ottsharing.protocol.UserDeleteResult;

import kr.flab.ottsharing.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Login 쪽 로직 개선으로 인해 동작하지 않는 코드
    @PostMapping("/login")
    public String IdCheck(@RequestParam String userId,HttpServletResponse response) {
        /*
        User loginMember = userService.loginCheck(userId);
        if (loginMember == null) {
            loginMember = userService.enrollUser(userId);
        }
        return loginMember;
        */
        return null;
    }

    @PutMapping("/myPage")
    public MyPageUpdateResult changeMyInfo(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        String password = request.get("password");
        String email = request.get("email");

        return userService.updateMyInfo(userId, password, email);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("memberId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/myPage")
    public UserDeleteResult leave() {
        String userId = "user"; // 추후 JWT login 구현되면 현재 로그인 된 아이디 가져오도록 수정
        return userService.deleteMyInfo(userId);
    }
      
    @GetMapping("/myPage")
    public MyInfo getMyInfo() {
        String userId = "user"; // 나중에 JWT login 구현되면 그에 맞게 자동으로 가져오도록 수정해야 함
        return userService.fetchMyInfo(userId);
    }

}
