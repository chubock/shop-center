package com.yubar.shopcenter.userservice.service;

import com.yubar.shopcenter.userservice.conf.ConfigurationHolder;
import com.yubar.shopcenter.userservice.entity.Authority;
import com.yubar.shopcenter.userservice.entity.Phone;
import com.yubar.shopcenter.userservice.entity.User;
import com.yubar.shopcenter.userservice.model.PhoneModel;
import com.yubar.shopcenter.userservice.model.UserModel;
import com.yubar.shopcenter.userservice.repository.PhoneRepository;
import com.yubar.shopcenter.userservice.repository.UserRepository;
import com.yubar.shopcenter.util.ExceptionUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.Instant;

@Service
@Transactional
public class RegistrationService {

    private final ConfigurationHolder configurationHolder;

    private final PhoneRepository phoneRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final SecureRandom random = new SecureRandom();

    public RegistrationService(
            ConfigurationHolder configurationHolder,
            PhoneRepository phoneRepository,
            UserRepository userRepository
    ) {
        this.configurationHolder = configurationHolder;
        this.phoneRepository = phoneRepository;
        this.userRepository = userRepository;
    }

    public PhoneModel registerPhone(String phoneNumber) {

        Phone phone = phoneRepository
                .findTopByNumberOrderByCreatedDateDesc(phoneNumber)
                .filter(this::checkVerificationCode)
                .orElseGet(() -> new Phone(phoneNumber, generateCode(configurationHolder.getVerificationCodeLength())));

        phoneRepository.save(phone);

        return PhoneModel.modelOf(phone);

    }

    public UserModel verifyPhone(String phoneId, String code, Authority authority) {

        Phone phone = phoneRepository
                .findById(phoneId)
                .filter(p -> p.getVerificationCode().equals(code))
                .filter(this::checkVerificationCode)
                .orElseThrow(ExceptionUtils::badRequest);

        phone.verify();

        String username;
        if (authority == Authority.SELLER)
            username = "S" + phone.getNumber();
        else if (authority == Authority.CUSTOMER)
            username = "C" + phone.getNumber();
        else
            throw ExceptionUtils.badRequest();

        User user = userRepository.findByUsername(username)
                .orElseGet(() -> new User(username, authority));

        String password = RandomStringUtils
                .randomAlphanumeric(configurationHolder.getGeneratedPasswordLength());

        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);

        UserModel userModel = UserModel.modelOf(user);
        userModel.setPassword(password);
        return userModel;

    }

    private Boolean checkVerificationCode(Phone vc) {

        if (vc.getState() != Phone.State.REGISTERED)
            return false;

        boolean expired = Instant
                .now()
                .minusSeconds(configurationHolder.getVerificationCodeTTLSeconds())
                .isAfter(vc.getCreatedDate().toInstant());

        if (expired)
            vc.expire();

        return !expired;

    }

    private String generateCode(int length) {
        int base = (int) Math.pow(10d, length - 1);
        return random.nextInt(9 * base) + base + "";
    }

}