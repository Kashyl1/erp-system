package com.example.erp.common.userprofile;

import com.example.erp.common.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_profile")
@Schema(description = "Entity representing additional user profile information")
public class UserProfile {

    @Id
    @GeneratedValue
    @Schema(description = "Unique identifier of the user profile", example = "1")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false, length = 50)
    @Schema(description = "First name of the user", example = "John")
    private String firstname;

    @Column(nullable = false, length = 50)
    @Schema(description = "Last name of the user", example = "Doe")
    private String lastname;

    @Column(length = 20)
    @Schema(description = "User's phone number", example = "+48123456789")
    private String phoneNumber;

    @Column(length = 200)
    @Schema(description = "User's address", example = "Street 123, City, Country")
    private String address;

    @Column
    @Schema(description = "User's date of birth", example = "1990-01-01")
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(description = "Department the user belongs to", example = "HR")
    private Department department;
}
