package com.example.springjwt.domain.mail.service;

import com.example.springjwt.domain.mail.error.MailError;
import com.example.springjwt.domain.mail.repository.MailRepository;
import com.example.springjwt.global.error.CustomException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final MailRepository mailRepository;
    private final JavaMailSender mailSender;

    @Override
    public String getRandomString(int length) {
        String randomUUID = UUID.randomUUID().toString();
        return randomUUID.substring(0, length);
    }

    @Override
    public void sendMail(String email) throws MessagingException {
        if (!isValidEmail(email)) {
            throw new CustomException(MailError.EMAIL_INVALID);
        }

        String randomString = getRandomString(6);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(email);
        helper.setSubject("툭 이메일 인증");
        helper.setText("인증 코드: " + randomString);

        mailSender.send(message);
        mailRepository.save(email, randomString);
    }

    @Override
    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

        return pattern.matcher(email).matches();
    }
}
