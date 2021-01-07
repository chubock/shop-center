package com.yubar.shopcenter.customerservice.service;

import com.yubar.shopcenter.customerservice.entity.Address;
import com.yubar.shopcenter.customerservice.entity.Customer;
import com.yubar.shopcenter.customerservice.model.AddressModel;
import com.yubar.shopcenter.customerservice.model.CustomerModel;
import com.yubar.shopcenter.customerservice.repository.AddressRepository;
import com.yubar.shopcenter.customerservice.repository.CustomerRepository;
import com.yubar.shopcenter.util.ExceptionUtils;
import com.yubar.shopcenter.util.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerService(
            CustomerRepository customerRepository,
            AddressRepository addressRepository
    ) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    public CustomerModel getCustomer() {

        Customer customer = customerRepository.findById(SecurityUtils.getUserId())
                .orElseThrow(ExceptionUtils::notFound);

        return CustomerModel.modelOf(customer);

    }

    public CustomerModel addCustomer(CustomerModel model) {

        Customer customer = customerRepository
                .findById(SecurityUtils.getUserId())
                .map(c -> {
                    c.setFirstName(model.getFirstName());
                    c.setLastName(model.getLastName());
                    return c;
                }).orElseGet(
                        () -> new Customer(SecurityUtils.getUserId(), model.getMobile(), model.getFirstName(), model.getLastName())
                );

        customerRepository.save(customer);

        return CustomerModel.modelOf(customer);

    }

    public List<AddressModel> getAddresses() {

        Customer customer = customerRepository.getOne(SecurityUtils.getUserId());

        return addressRepository.findByCustomer(customer)
                .stream()
                .map(AddressModel::modelOf)
                .collect(Collectors.toList());


    }

    public AddressModel addAddress(AddressModel model) {

        Customer customer = customerRepository.findById(SecurityUtils.getUserId())
                .orElseThrow(ExceptionUtils::badRequest);

        Address address = new Address(customer, model.getLocation(), model.getText());
        address.setPhoneNumber(model.getPhoneNumber());
        addressRepository.save(address);

        return AddressModel.modelOf(address);

    }
}
