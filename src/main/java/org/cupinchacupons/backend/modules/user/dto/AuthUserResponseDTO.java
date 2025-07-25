package org.cupinchacupons.backend.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserResponseDTO {

    private String access_token;
    private String expires_in;
    private List<String> role;
}