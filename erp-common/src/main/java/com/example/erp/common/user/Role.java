package com.example.erp.common.user;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing user roles in the system.
 */
@Schema(description = "User roles in the system")
public enum Role {

    @Schema(description = "Regular user role")
    ROLE_USER,

    @Schema(description = "Administrator role")
    ROLE_ADMIN,

    @Schema(description = "Super administrator role")
    ROLE_SUPER_ADMIN
}
