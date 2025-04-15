package com.sparta.yuni.acceptance.steps;

import com.sparta.yuni.auth.application.dto.SendEmailRequestDto;
import io.restassured.RestAssured;
import org.springframework.http.MediaType;

public class SingUpAcceptanceSteps {

    public static Integer requestSendEmail(SendEmailRequestDto dto) {
        return RestAssured.given().body(dto).contentType(MediaType.APPLICATION_JSON_VALUE).when()
            .post("/signup/send-verification-email").then().extract().jsonPath().get("code");
    }
}
