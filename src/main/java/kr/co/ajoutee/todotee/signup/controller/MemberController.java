package kr.co.ajoutee.todotee.signup.controller;

import kr.co.ajoutee.todotee.signup.dto.TokenInfo;
import kr.co.ajoutee.todotee.signup.service.MemberService;
import kr.co.ajoutee.todotee.signup.dto.LoginDto;
import kr.co.ajoutee.todotee.signup.dto.MemberFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt-login")
@CrossOrigin
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<TokenInfo> login(@RequestBody LoginDto registerRequestDto) {
        return ResponseEntity.ok(service.login(registerRequestDto));
    }

    //회원가입
    @PostMapping("/register")
    public ResponseEntity<TokenInfo> register(@RequestBody MemberFormDto registerRequestDto) {
        return ResponseEntity.ok(service.register(registerRequestDto));
    }


}




