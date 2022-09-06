package com.toceansoft.common.entity;

import lombok.Data;

@Data
public class PasswordInfo {
    private String oldPassword;
    private String newPassword;
    private String veriedPassword;
}
