package kr.co.ajoutee.todotee.signup.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {

    private String accessToken;
//    private String refreshToken; // 추후 추가 예정

}
