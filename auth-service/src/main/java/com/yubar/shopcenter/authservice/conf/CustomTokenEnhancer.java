package com.yubar.shopcenter.authservice.conf;

import com.yubar.shopcenter.authservice.entity.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        User user = (User) oAuth2Authentication.getUserAuthentication().getPrincipal();

        Map<String, Object> additionalProperties = new HashMap<>();
        additionalProperties.put("userId", user.getId());

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalProperties);

        return oAuth2AccessToken;
    }
}