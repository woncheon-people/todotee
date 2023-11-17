package kr.co.ajoutee.todotee.signup.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberFormDto {


    @NotBlank
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String pwd;


}
