package com.sparta.yuni.acceptance.auth;

import static com.sparta.yuni.acceptance.steps.SingUpAcceptanceSteps.requestSendEmail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.sparta.yuni.acceptance.utils.AcceptanceTestTemplate;
import com.sparta.yuni.auth.application.dto.SendEmailRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//자동으로 관련된 템플릿들이 받아지기 때문에 상속받기
class SignUpAcceptanceTest extends AcceptanceTestTemplate {

    private final String email  = "email@email.com";

    @BeforeEach
    public void setUp() {
        this.cleanUp();
    }

    @Test
    void givenEmail_whenSendEmail_thenVerificationTokenSaved(){
        // given
        SendEmailRequestDto dto = new SendEmailRequestDto(email);

        //when
        Integer code = requestSendEmail(dto);

        //then
        String token = this.getEmailToken(email);
        assertNotNull(token);
        assertEquals(0, code);
    }

    @Test
    void givenInvalidEmail_whenEmailSend_thenVerificationTokenNotSaved(){
        //given
         SendEmailRequestDto dto = new SendEmailRequestDto(email);
        // when
        Integer code = requestSendEmail(dto);

        //then
        String token = this.getEmailToken(email);
        assertNull(token);
        assertEquals(500, code);
    }

}
