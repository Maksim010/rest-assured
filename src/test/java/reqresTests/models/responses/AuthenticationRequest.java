package reqresTests.models.responses;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthenticationRequest {

    @NotBlank
    String token;
}
