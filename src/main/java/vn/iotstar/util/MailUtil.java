package vn.iotstar.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class MailUtil {

    // THAY BẰNG GMAIL CỦA BẠN
    private static final String FROM_EMAIL = "ttttan1233210@gmail.com";

    // THAY BẰNG GOOGLE APP PASSWORD
    private static final String APP_PASSWORD = "dpii lnbq wdyg pdlf";

    public static void sendOTP(String toEmail, String otp) throws Exception {

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(
                props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication(
                                FROM_EMAIL,
                                APP_PASSWORD
                        );
                    }
                }
        );

        Message message = new MimeMessage(session);

        message.setFrom(
                new InternetAddress(FROM_EMAIL)
        );

        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(toEmail)
        );

        message.setSubject("Book Store - Ma OTP kich hoat tai khoan");

        message.setText(
                "Xin chao,\n\n"
                + "Ma OTP cua ban la: " + otp + "\n\n"
                + "Ma OTP co hieu luc trong thoi gian dang ky.\n"
                + "Vui long khong chia se ma OTP nay cho nguoi khac.\n\n"
                + "Book Store"
        );

        Transport.send(message);
    }
}