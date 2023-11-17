package kr.co.ajoutee.todotee.security;


import kr.co.ajoutee.todotee.security.jwt.CorsFilterConfig;
import kr.co.ajoutee.todotee.security.jwt.JwtAuthenticationFilter;
import kr.co.ajoutee.todotee.security.jwt.exception.JwtAccessDeniedHandler;
import kr.co.ajoutee.todotee.security.jwt.exception.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.stream.Stream;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    @Value("${jwt.secret}")
//    private String secretKey;
//    private final JwtTokenProvider tokenProvider;
    private final CorsFilterConfig corsFilterConfig;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    private static final String[] PERMIT_URL_ARRAY = {"/error"};


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {


        return http
                .httpBasic(httpBasic -> httpBasic.disable()) //
                .csrf(AbstractHttpConfigurer::disable)
                //h2 database 사용을 위한 xFrameOption 설정
                .headers(header -> header.frameOptions(
                                        frameOptionsConfig -> frameOptionsConfig.disable()))
                //여러 필터를 사용 중이라면 CorsFilter를 제일 앞에 설정
                .addFilterBefore(corsFilterConfig.corsFilter(), UsernamePasswordAuthenticationFilter.class)
                //에러 커스텀 설정
                .exceptionHandling(authenticationManager -> authenticationManager
                        .accessDeniedHandler(new JwtAccessDeniedHandler())
                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                )
                // form 기반의 로그인에 대해 비 활성화하며 커스텀으로 구성한 필터 사용
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable())
                //Session 기반의 인증기반을 사용하지 않기 위한 설정
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                //토큰을 활용하는 경우 모든 요청에 대해 '인가'에 대해서 사용.
                .authorizeHttpRequests(authorize ->
                        authorize
                                //h2 console 활용을 위한 인가 설정
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                // URL 패턴별로 접근 권한 설정을 위한 Antrequestmathcher 사용
                                .requestMatchers(new AntPathRequestMatcher("/jwt-login/**")).permitAll()
//                                .requestMatchers("/","/**").permitAll()
//                                .requestMatchers("jwt-login/**").permitAll()
//                                .requestMatchers(PERMIT_URL_ARRAY).permitAll()
//                                .requestMatchers(new MvcRequestMatcher(introspector,"/**")).permitAll()
//                                .anyRequest().hasRole("USER")


                )

                // 커스텀 인가 필터 추가
                .addFilterBefore(jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
