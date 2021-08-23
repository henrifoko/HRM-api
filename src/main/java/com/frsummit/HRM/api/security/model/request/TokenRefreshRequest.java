package com.frsummit.HRM.api.security.model.request;

import javax.validation.constraints.NotBlank;

public class TokenRefreshRequest {

    @NotBlank
    private String refreshToken;

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken( String refreshToken ) {
        this.refreshToken = refreshToken;
    }

}
