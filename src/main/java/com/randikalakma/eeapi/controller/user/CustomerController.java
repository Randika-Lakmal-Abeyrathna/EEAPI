package com.randikalakma.eeapi.controller.user;

import com.randikalakma.eeapi.dto.CustomerRequest;
import com.randikalakma.eeapi.model.CustomerInfo;
import com.randikalakma.eeapi.service.UserService;
import com.randikalakma.eeapi.service.user.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<CustomerInfo>> getAllCustomers() {
        List<CustomerInfo> customerList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);

    }

    @PostMapping("/find/email")
    public ResponseEntity<CustomerInfo> getCustomerByEmail(@RequestParam("email") String email) {
        CustomerInfo customer = customerService.getCustomerByEmailAndUserType(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerInfo> addCustomer(@RequestBody CustomerRequest customer) {
        CustomerInfo customerInfo = customerService.addCustomer(customer);
        return new ResponseEntity<>(customerInfo, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerInfo> updateCustomer(@RequestBody CustomerRequest customer) {
        CustomerInfo customerInfo =customerService.updateCustomer(customer);
        return new ResponseEntity<>(customerInfo,HttpStatus.OK);
    }

    @PutMapping("/update/image")
    public ResponseEntity<?> addCustomerImage(@RequestParam("email") String email, @RequestParam("image") MultipartFile imageFile) {
        userService.updateUserImage(email, imageFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
