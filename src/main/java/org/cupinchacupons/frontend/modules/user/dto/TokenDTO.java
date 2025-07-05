package org.cupinchacupons.frontend.modules.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class TokenDTO {
    private String access_token;
    private String expires_in;
    private List<String> role;

}
