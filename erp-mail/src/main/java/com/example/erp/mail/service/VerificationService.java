package com.example.erp.mail.service;

import com.example.erp.common.user.UserRepository;
import com.example.erp.mail.exceptions.EmailSendingException;
import com.example.erp.common.user.User;
import com.example.erp.mail.exceptions.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Tag(name = "Verification Service", description = "Service for handling email verification and notifications")
public class VerificationService {
    private final JavaMailSender mailSender;
    private final UserRepository userRepository;

    @Value("${app.base-url}")
    private String baseUrl;

    @Operation(summary = "Generate reset token", description = "Generates a unique token for password reset")
    public String generateResetToken() {
        return UUID.randomUUID().toString();
    }

    @Operation(summary = "Send welcome email", description = "Sends a welcome email to the newly created user")
    public void sendWelcomeEmail(User user) {
        String subject = "Your Account Has Been Created – Royal Coin ERP";

        String textMessage = "Dear " + user.getProfile().getFirstname() + ",\n\n" +
                "We are pleased to inform you that your account has been successfully created in the Royal Coin ERP system.\n\n" +
                "You can now log in using the following credentials:\n" +
                "Email: " + user.getEmail() + "\n" +
                "Temporary password: Please contact your administrator for login details.\n\n" +
                "For security reasons, we recommend changing your password after your first login.\n\n" +
                "If you have any questions or require assistance, please contact your administrator or IT support.\n\n" +
                "Best regards,\n" +
                "HR Team – Royal Coin ERP";

        String htmlMessage = "<html>" +
                "<body style='font-family: Arial, sans-serif;'>" +
                "<p>Dear " + user.getProfile().getFirstname() + ",</p>" +
                "<p>We are pleased to inform you that your account has been successfully created in the <strong>Royal Coin ERP</strong> system.</p>" +
                "<p>You can now log in using the following credentials:</p>" +
                "<ul>" +
                "<li><strong>Email:</strong> " + user.getEmail() + "</li>" +
                "<li><strong>Temporary password:</strong> Please contact your administrator for login details.</li>" +
                "</ul>" +
                "<p>For security reasons, we recommend changing your password after your first login.</p>" +
                "<p>If you have any questions or require assistance, please contact your administrator or IT support.</p>" +
                "<br>" +
                "<p>Best regards,<br>HR Team – Royal Coin ERP</p>" +
                "<hr>" +
                "<p style='font-size: small;'>If you have any questions, feel free to contact us at <a href='mailto:RoyalCoinSupport@gmail.com'>RoyalCoinSupport@gmail.com</a>.</p>" +
                "</body>" +
                "</html>";

        MimeMessage mimeMessage;
        try {
            mimeMessage = mailSender.createMimeMessage();
        } catch (MailException e) {
            throw new EmailSendingException("Failed to create email message for: " + user.getEmail());
        }

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
            helper.setFrom("kamilsmtp@gmail.com", "HR Team – Royal Coin ERP");
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(textMessage, htmlMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new EmailSendingException("Failed to construct email message for: " + user.getEmail());
        }

        try {
            mailSender.send(mimeMessage);
        } catch (MailException e) {
            throw new EmailSendingException("Failed to send email to: " + user.getEmail());
        }
    }

    @Operation(summary = "Send password reset email", description = "Sends a password reset email to the user")
    public void sendPasswordResetEmail(User user, String resetToken) {
        String subject = "Password Reset Request – Royal Coin ERP";
        String resetUrl = baseUrl + "/reset-password?token=" + resetToken;

        String textMessage = "Dear " + user.getProfile().getFirstname() + ",\n\n" +
                "We have received your request to reset your password for your account in the Royal Coin ERP system.\n" +
                "To proceed with resetting your password, please click on the following link:\n" +
                resetUrl + "\n\n" +
                "If you did not initiate this request, please contact our support team immediately.\n\n" +
                "Sincerely,\n" +
                "HR Team – Royal Coin ERP";

        String htmlMessage = "<html>" +
                "<body style='font-family: Arial, sans-serif;'>" +
                "<p>Dear " + user.getProfile().getFirstname() + ",</p>" +
                "<p>We have received your request to reset your password for your account in the <strong>Royal Coin ERP</strong> system.</p>" +
                "<p>To proceed with resetting your password, please click the button below:</p>" +
                "<p style='text-align: center;'>" +
                "<a href='" + resetUrl + "' style='background-color: #f44336; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;'>Reset Password</a>" +
                "</p>" +
                "<p>If the button above does not work, please copy and paste the following link into your browser:</p>" +
                "<p><a href='" + resetUrl + "'>" + resetUrl + "</a></p>" +
                "<p>If you did not initiate this password reset request, please contact our support team immediately.</p>" +
                "<br>" +
                "<p>Sincerely,<br>HR Team – Royal Coin ERP</p>" +
                "<hr>" +
                "<p style='font-size: small;'>For further assistance, please contact our support at <a href='mailto:RoyalCoinSupport@gmail.com'>RoyalCoinSupport@gmail.com</a>.</p>" +
                "</body>" +
                "</html>";

        MimeMessage mimeMessage;
        try {
            mimeMessage = mailSender.createMimeMessage();
        } catch (MailException e) {
            throw new EmailSendingException("Failed to create email message for: " + user.getEmail());
        }

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
            helper.setFrom("kamilsmtp@gmail.com", "HR Team – Royal Coin ERP");
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(textMessage, htmlMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new EmailSendingException("Failed to construct email message for: " + user.getEmail());
        }
        try {
            mailSender.send(mimeMessage);
        } catch (MailException e) {
            throw new EmailSendingException("Failed to send email to: " + user.getEmail());
        }
    }


    @Operation(summary = "Send password reset link", description = "Initiates the password reset process by sending a reset link")
    public void sendPasswordResetLink(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with the given email does not exist"));

        String resetToken = generateResetToken();
        user.setPasswordResetToken(resetToken);

        userRepository.save(user);

        try {
            sendPasswordResetEmail(user, resetToken);
        } catch (Exception e) {
            throw new EmailSendingException("Failed to send password reset email");
        }
    }
}