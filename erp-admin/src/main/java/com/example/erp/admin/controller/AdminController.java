package com.example.erp.admin.controller;

import com.example.erp.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/users/{id}/block")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> blockUser(@PathVariable("id") Integer userId) {
        adminService.blockUser(userId);
        return ResponseEntity.ok("User blocked successfully.");
    }

    @PostMapping("/users/{id}/unblock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> unblockUser(@PathVariable("id") Integer userId) {
        adminService.unblockUser(userId);
        return ResponseEntity.ok("User unblocked successfully.");
    }
}
