package exchangeApp.security;

import exchangeApp.security.DTO.SecurityUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class AuthenticationController {

    private final AuthenticationService service;

    @Autowired
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody SecurityUserDTO dto){

    return ResponseEntity.ok(service.getAuthResponseDto(dto));
    }

    @PostMapping("/register")
    public String register(@RequestBody SecurityUserDTO dto){
        return service.registerNewUser(dto);
    }
}
