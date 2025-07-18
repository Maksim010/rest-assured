package reqresTests;

import reqresTests.models.requests.AuthDto;

public class TestData {

    public static AuthDto createAuthDtoWithEmailAndPassword(String email, String password) {

        return  AuthDto.builder()
                .email(email)
                .password(password)
                .build();
    }
}
