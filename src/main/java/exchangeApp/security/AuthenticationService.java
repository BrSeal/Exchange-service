package exchangeApp.security;

import exchangeApp.security.DTO.SecurityUserDTO;

import java.util.Map;

public interface AuthenticationService {
    Map<Object,Object> getAuthResponseDto(SecurityUserDTO dto);

    String registerNewUser(SecurityUserDTO dto);
}
