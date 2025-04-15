package com.sparta.yuni.auth.ui;

import com.sparta.yuni.auth.application.dto.SendEmailRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sparta.yuni.common.ui.Response;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    @PostMapping("/send-verification-email")
    public Response<Void> sendEmail(@RequestBody SendEmailRequestDto dto ){
        return Response.ok(null);
    }

}
