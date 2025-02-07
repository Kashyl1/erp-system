package com.example.erp.mail.controller;

import com.example.erp.common.exceptions.ErrorResponse;
import com.example.erp.mail.dto.ForgotPasswordRequest;
import com.example.erp.common.user.UserRepository;
import com.example.erp.mail.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
@Tag(name = "Verification Mail Controller", description = "Handles email verification processes")
public class VerificationMailController {

    private final UserRepository userRepository;
    private final VerificationService verificationService;

    @PostMapping("/forgot-password")
    @Operation(summary = "Forgot password", description = "Sends a password reset link to the user's email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password reset link sent successfully"),
            @ApiResponse(responseCode = "404", description = "Email not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        verificationService.sendPasswordResetLink(request.getEmail());
        return ResponseEntity.ok("Password reset link has been sent to your email.");
    }

}