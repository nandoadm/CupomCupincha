package org.cupinchacupons.frontend.modules.dto;

import lombok.Data;

@Data
public class TokenDTO {
    private String access_token;
    private String expires_in;
}
