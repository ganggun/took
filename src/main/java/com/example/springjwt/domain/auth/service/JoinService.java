package com.example.springjwt.domain.auth.service;

import com.example.springjwt.domain.auth.dto.JoinDTO;
import com.example.springjwt.domain.auth.entity.UserEntity;
import com.example.springjwt.domain.auth.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO) {

        String id = joinDTO.getId();
        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();
        String studentNumber = joinDTO.getStudentNumber();

        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {

            return;
        }

        UserEntity data = new UserEntity();

        data.setId(id);
        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setStudentNumber(studentNumber);
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}
