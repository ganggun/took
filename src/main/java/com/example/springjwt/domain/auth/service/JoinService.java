package com.example.springjwt.domain.auth.service;

import com.example.springjwt.domain.auth.dto.JoinDTO;
import com.example.springjwt.domain.auth.entity.UserEntity;
import com.example.springjwt.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) throws BadRequestException {

        if(userRepository.findByUsername(joinDTO.getUsername()) != null) {
            throw new BadRequestException("Username is already in use");
        }

        userRepository.save(UserEntity.builder()
                .username(joinDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(joinDTO.getPassword()))
                .studentNumber(joinDTO.getStudentNumber())
                .build());
    }
}
