package kr.co.ajoutee.todotee.service;

import kr.co.ajoutee.todotee.dto.SignupDTO;
import kr.co.ajoutee.todotee.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);
}
