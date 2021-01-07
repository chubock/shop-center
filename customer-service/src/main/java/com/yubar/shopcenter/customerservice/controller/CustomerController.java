package com.yubar.shopcenter.customerservice.controller;

import com.yubar.shopcenter.customerservice.model.AddressModel;
import com.yubar.shopcenter.customerservice.model.CustomerModel;
import com.yubar.shopcenter.security.IsCustomer;
import com.yubar.shopcenter.customerservice.service.CustomerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("${api-prefix}/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @IsCustomer
    @GetMapping("/me")
    public CustomerModel me() {
        return customerService.getCustomer();
    }

    @IsCustomer
    @PutMapping("/me")
    public CustomerModel register(@Validated @RequestBody CustomerModel model) {
        return customerService.addCustomer(model);
    }

    @IsCustomer
    @GetMapping("/me/addresses")
    public List<AddressModel> getAddresses() {
        return customerService.getAddresses();
    }

    @IsCustomer
    @PostMapping("/me/addresses")
    public AddressModel addAddress(@Validated @RequestBody AddressModel model) {
        return customerService.addAddress(model);
    }


}
