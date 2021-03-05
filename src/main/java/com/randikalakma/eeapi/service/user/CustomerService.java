package com.randikalakma.eeapi.service.user;

import com.randikalakma.eeapi.dto.CustomerRequest;
import com.randikalakma.eeapi.exception.user.CustomerException;
import com.randikalakma.eeapi.model.*;
import com.randikalakma.eeapi.repository.*;
import com.randikalakma.eeapi.service.UserService;
import com.randikalakma.eeapi.service.admin.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class CustomerService {


    private final PasswordEncoder passwordEncoder;
    private final CityService cityService;
    private final StatusService statusService;
    private final UserTypeService userTypeService;
    private final GenderService genderService;
    private final SalutationService salutationService;
    private final CustomerInfoRepository customerInfoRepository;
    private final UserService userService;

    public CustomerInfo addCustomer(CustomerRequest customerRequest) {
        customerValidation(customerRequest);
        customerRequest.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
        City city = cityService.findCityByCityName(customerRequest.getCity().toLowerCase());
        Status status = statusService.findStatusByStatus(customerRequest.getStatus().toLowerCase());
        UserType userType = userTypeService.getUserTypeByUserType(customerRequest.getUserType().toLowerCase());
        Gender gender = genderService.findGenderByGender(customerRequest.getGender().toLowerCase());
        Salutation salutation = salutationService.findSalutationBySalutation(customerRequest.getSalutation().toLowerCase());

        // Save User details
        User user = new User();
        user.setEmail(customerRequest.getEmail());
        user.setAddressNo(customerRequest.getAddressNo());
        user.setAddress_street(customerRequest.getAddressStreet1());
        user.setAddress_street2(customerRequest.getAddressStreet2());
        user.setContactNumber1(customerRequest.getContactNumber1());
        user.setContactNumber2(customerRequest.getContactNumber2());
        user.setPassword(customerRequest.getPassword());
        user.setEnabled(false);
        user.setCity(city);
        user.setStatus(status);
        user.setUserType(userType);

        User addedUser = userService.addUser(user);

        //Save Customer Info
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setFirstName(customerRequest.getFirstName());
        customerInfo.setMiddleName(customerRequest.getMiddleName());
        customerInfo.setLastName(customerRequest.getLastName());
        customerInfo.setDateOfBirth(customerRequest.getDateOfBirth());
        customerInfo.setUser(addedUser);
        customerInfo.setGender(gender);
        customerInfo.setSalutation(salutation);

        CustomerInfo savedCustomerInfo =customerInfoRepository.save(customerInfo);
        userService.sendUserActivationEmail(addedUser);
        return savedCustomerInfo;
    }

    public List<CustomerInfo> getAllCustomers() {
        return customerInfoRepository.findAll();
    }

    public CustomerInfo getCustomerByEmailAndUserType(String email) {

        UserType userType = userTypeService.getUserTypeByUserType("user");
        User user = userService.findUserByEmailAndUserType(email, userType);
        return customerInfoRepository.findByUser(user)
                .orElseThrow(() -> new CustomerException("Customer Info Not found"));
    }

    public CustomerInfo updateCustomer(CustomerRequest customerRequest) {
        customerUpdateValidation(customerRequest);
        customerRequest.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
        City city = cityService.findCityByCityName(customerRequest.getCity().toLowerCase());
        Status status = statusService.findStatusByStatus(customerRequest.getStatus().toLowerCase());
        UserType userType = userTypeService.getUserTypeByUserType(customerRequest.getUserType().toLowerCase());
        Gender gender = genderService.findGenderByGender(customerRequest.getGender().toLowerCase());
        Salutation salutation = salutationService.findSalutationBySalutation(customerRequest.getSalutation().toLowerCase());

        String userEmail = customerRequest.getEmail();

        // Update User details
        User user = userService.getUserByEmail(userEmail);
        CustomerInfo customerInfo = customerInfoRepository.findByUser(user)
                .orElseThrow(() -> new CustomerException("Customer Info Not found"));

        user.setAddressNo(customerRequest.getAddressNo());
        user.setAddress_street(customerRequest.getAddressStreet1());
        user.setAddress_street2(customerRequest.getAddressStreet2());
        user.setContactNumber1(customerRequest.getContactNumber1());
        user.setContactNumber2(customerRequest.getContactNumber2());
        user.setPassword(customerRequest.getPassword());
        user.setCity(city);
        user.setStatus(status);
        user.setUserType(userType);

        User addedUser = userService.updateUser(user);

        //update Customer Info
        customerInfo.setFirstName(customerRequest.getFirstName());
        customerInfo.setMiddleName(customerRequest.getMiddleName());
        customerInfo.setLastName(customerRequest.getLastName());
        customerInfo.setDateOfBirth(customerRequest.getDateOfBirth());
        customerInfo.setUser(addedUser);
        customerInfo.setGender(gender);
        customerInfo.setSalutation(salutation);

        return customerInfoRepository.save(customerInfo);
    }

    private void customerUpdateValidation(CustomerRequest customerRequest){

        String customerEmail = customerRequest.getEmail();
        Date customerBirthDay = customerRequest.getDateOfBirth();
        String customerFirstName = customerRequest.getFirstName();
        String password = customerRequest.getPassword();

        if (customerEmail.isEmpty() || customerEmail.isBlank())
            throw new CustomerException("Email id cannot be empty or blank");
        if (customerBirthDay.after(new Date())) {
            throw new CustomerException("Invalid Date of birth");
        }
        if (customerFirstName.isBlank() || customerFirstName.isEmpty()) {
            throw new CustomerException("Customer First Name cannot be empty or blank");
        }
        if (password.isEmpty() || password.isBlank()) {
            throw new CustomerException("Password cannot be empty or blank");
        }

    }

    private void customerValidation(CustomerRequest customerRequest) {

        String customerEmail = customerRequest.getEmail();
        Date customerBirthDay = customerRequest.getDateOfBirth();
        String customerFirstName = customerRequest.getFirstName();
        String password = customerRequest.getPassword();

        if (customerEmail.isEmpty() || customerEmail.isBlank())
            throw new CustomerException("Email id cannot be empty or blank");
        if (userService.checkUserExistByEmail(customerEmail)) {
            throw new CustomerException("Email id " + customerRequest.getEmail() + " already is use.");
        }
        if (customerBirthDay.after(new Date())) {
            throw new CustomerException("Invalid Date of birth");
        }
        if (customerFirstName.isBlank() || customerFirstName.isEmpty()) {
            throw new CustomerException("Customer First Name cannot be empty or blank");
        }
        if (password.isEmpty() || password.isBlank()) {
            throw new CustomerException("Password cannot be empty or blank");
        }

    }

}
