package exchangeApp.security;

import exchangeApp.security.DTO.SecurityUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody SecurityUserDTO dto){

    return ResponseEntity.ok(service.getAuthResponseDto(dto));
    }

    @PostMapping("/register")
    public String register(@RequestBody SecurityUserDTO dto){
        return service.registerNewUser(dto);
    }
}
