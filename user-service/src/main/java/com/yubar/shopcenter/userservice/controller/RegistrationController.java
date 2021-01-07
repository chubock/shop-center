package com.yubar.shopcenter.userservice.controller;

import com.yubar.shopcenter.userservice.entity.Authority;
import com.yubar.shopcenter.userservice.model.PhoneModel;
import com.yubar.shopcenter.userservice.model.UserModel;
import com.yubar.shopcenter.userservice.service.RegistrationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("${api-prefix}")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/phones")
    public PhoneModel registerPhone(@Validated @RequestBody RegisterPhoneRequest request) {
        return registrationService.registerPhone(request.phoneNumber);
    }

    @PostMapping("/phones/{id}")
    public UserModel verifyPhone(@PathVariable String id, @Validated @RequestBody VerifyPhoneRequest request) {
        return registrationService.verifyPhone(id, request.code, request.authority);
    }



    public static class RegisterPhoneRequest {

        @NotEmpty
        private String phoneNumber;

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

    }
    public static class VerifyPhoneRequest {

        @NotEmpty
        private String code;

        @NotNull
        private Authority authority;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Authority getAuthority() {
            return authority;
        }

        public void setAuthority(Authority authority) {
            this.authority = authority;
        }
    }
}
