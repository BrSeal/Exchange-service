package exchangeApp.security;

import exchangeApp.security.DTO.SecurityUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/user/roles")
        public List<String> getRoles(Authentication authentication){
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}
