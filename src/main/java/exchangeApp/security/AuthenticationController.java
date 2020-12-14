package exchangeApp.security;

import exchangeApp.security.DTO.AuthResponseDto;
import exchangeApp.security.DTO.SecurityUserDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/authenticate")
    @ApiOperation(
            value = "Provides authorization",
            notes = "Provides token-based authorization")
    @ApiResponses({
            @ApiResponse(
                    code = 200, message = "Successfully authenticated",
                    response = AuthResponseDto.class),
            @ApiResponse(code = 500, message = "If username or password is invalid")
    })
    public AuthResponseDto authenticate(
            @ApiParam(value = "Holds username and password")
            @RequestBody SecurityUserDTO dto) {
        return service.getAuthResponseDto(dto);
    }

    @PostMapping("/register")
    @ApiOperation(
            value = "Provides registration",
            notes = "You can't register admin by this way")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully registered"),
            @ApiResponse(code = 400, message = "Registration fails because username already exists")
    })
    public String register(
            @ApiParam(value = "Holds username and password")
            @RequestBody SecurityUserDTO dto) {
        return service.registerNewUser(dto);
    }


    @ApiOperation(
            value = "Sends list of current user roles",
            notes = "Can be reached only by authenticated users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "If user is logged in returns him list of his roles", response = String[].class),
            @ApiResponse(code = 400, message = "If not logged in")
    })
    @GetMapping("/user/roles")
    public List<String> getRoles(Authentication authentication) {
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}
