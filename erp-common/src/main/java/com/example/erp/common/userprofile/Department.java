package com.example.erp.common.userprofile;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Departments in the organization")
public enum Department {
    HR,
    FINANCE,
    IT,
    SALES,
    MARKETING
}
