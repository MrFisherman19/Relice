package com.mrfisherman.relice.Service.Email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

public interface EmailService {

    void sendEmail(SimpleMailMessage email);

}
