package kr.co.ajoutee.todotee.signup.service;

import kr.co.ajoutee.todotee.security.jwt.JwtService;
import kr.co.ajoutee.todotee.signup.dto.LoginDto;
import kr.co.ajoutee.todotee.signup.dto.MemberFormDto;
import kr.co.ajoutee.todotee.signup.dto.TokenInfo;
import kr.co.ajoutee.todotee.signup.model.Member;
import kr.co.ajoutee.todotee.signup.model.Role;
import kr.co.ajoutee.todotee.signup.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder encoder;
    private final MemberJpaRepository repository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;




    public TokenInfo login(LoginDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getPwd())
        );
        var user = repository.findByLoginId(request.getLoginId()).orElseThrow();
        var jwt = jwtService.generateToken(user);
        return TokenInfo.builder()
                .accessToken(jwt)
                .build();
    }

    public TokenInfo register(MemberFormDto request) {
        var user = Member.builder()
                .loginId(request.getLoginId())
                .pwd(encoder.encode(request.getPwd()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwt = jwtService.generateToken(user);
        return TokenInfo.builder()
                .accessToken(jwt)
                .build();
    }





}
