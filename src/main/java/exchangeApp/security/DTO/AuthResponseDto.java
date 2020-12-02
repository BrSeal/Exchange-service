package exchangeApp.security.DTO;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String username;
    private String token;
}
