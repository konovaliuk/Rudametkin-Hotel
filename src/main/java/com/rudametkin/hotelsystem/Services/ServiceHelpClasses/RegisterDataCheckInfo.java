package com.rudametkin.hotelsystem.Services.ServiceHelpClasses;

public class RegisterDataCheckInfo {
    private boolean isUniqueLogin;
    private boolean isUniqueEmail;
    private boolean isUniquePhone;

    public RegisterDataCheckInfo() {
        isUniqueLogin = true;
        isUniqueEmail = true;
        isUniquePhone = true;
    }

    public void setIsUniqueLogin(boolean isUniqueLogin) {
        this.isUniqueLogin = isUniqueLogin;
    }

    public void setIsUniqueEmail(boolean isUniqueEmail) {
        this.isUniqueEmail = isUniqueEmail;
    }

    public void setIsUniquePhone(boolean isUniquePhone) {
        this.isUniquePhone = isUniquePhone;
    }

    public boolean getIsUniqueLogin() {
        return isUniqueLogin;
    }

    public boolean getIsUniqueEmail() {
        return isUniqueEmail;
    }

    public boolean getIsUniquePhone() {
        return isUniquePhone;
    }

    public boolean isRegisterAvailable() {
        return isUniqueLogin && isUniqueEmail && isUniquePhone;
    }
}
