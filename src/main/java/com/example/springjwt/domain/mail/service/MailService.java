package com.example.springjwt.domain.mail.service;

import jakarta.mail.MessagingException;

public interface MailService {
    String getRandomString(int length);
    void sendMail(String email) throws MessagingException;
    boolean isValidEmail(String email);
}
