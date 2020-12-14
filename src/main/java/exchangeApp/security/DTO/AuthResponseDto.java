package exchangeApp.security.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "This model is sent if user successfully logged in")
public class AuthResponseDto {
    @ApiModelProperty(name = "username")
    private String username;
    @ApiModelProperty(name = "JWT-token")
    private String token;
}
