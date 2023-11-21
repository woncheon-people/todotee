package kr.co.ajoutee.todotee.service;

import kr.co.ajoutee.todotee.domain.Users;
import kr.co.ajoutee.todotee.dto.SignupDTO;
import kr.co.ajoutee.todotee.dto.UserDTO;
import kr.co.ajoutee.todotee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDTO createUser(SignupDTO signupDTO) {
        Users users = new Users();
        users.setName(signupDTO.getName());
        users.setEmail(signupDTO.getEmail());
        users.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
        Users createdUsers = userRepository.save(users);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUsers.getId());
        userDTO.setEmail(createdUsers.getEmail());
        userDTO.setName(createdUsers.getName());
        return userDTO;
    }
}
