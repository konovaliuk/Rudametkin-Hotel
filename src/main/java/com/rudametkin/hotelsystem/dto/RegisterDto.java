package com.rudametkin.hotelsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterDto {
    private boolean isUniqueLogin = false;
    private boolean isUniqueEmail = false;
    private boolean isUniquePhone = false;
    public boolean isRegisterAvailable() {
        return isUniqueLogin && isUniqueEmail && isUniquePhone;
    }
}