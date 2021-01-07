package com.yubar.shopcenter.userservice.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationHolder {

    @Value("${shop-center.user-service.verification-code-ttl-seconds}")
    private Integer verificationCodeTTLSeconds;

    @Value("${shop-center.user-service.verification-code-length}")
    private Integer verificationCodeLength;

    @Value("${shop-center.user-service.generated-password-length}")
    private Integer generatedPasswordLength;


    public Integer getVerificationCodeTTLSeconds() {
        return verificationCodeTTLSeconds;
    }

    private void setVerificationCodeTTLSeconds(Integer verificationCodeTTLSeconds) {
        this.verificationCodeTTLSeconds = verificationCodeTTLSeconds;
    }

    public Integer getVerificationCodeLength() {
        return verificationCodeLength;
    }

    public void setVerificationCodeLength(Integer verificationCodeLength) {
        this.verificationCodeLength = verificationCodeLength;
    }

    public Integer getGeneratedPasswordLength() {
        return generatedPasswordLength;
    }

    public void setGeneratedPasswordLength(Integer generatedPasswordLength) {
        this.generatedPasswordLength = generatedPasswordLength;
    }
}
