package exchangeApp.security;

import exchangeApp.security.DTO.AuthResponseDto;
import exchangeApp.security.DTO.SecurityUserDTO;

public interface AuthenticationService {
    AuthResponseDto getAuthResponseDto(SecurityUserDTO dto);

    String registerNewUser(SecurityUserDTO dto);
}
