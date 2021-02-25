package com.randikalakma.eeapi.controller.user;

import com.randikalakma.eeapi.model.Customer;
import com.randikalakma.eeapi.repository.ImageDataRepository;
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

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customerList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);

    }

    @PostMapping("/find/email")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestParam("email") String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customer);
        return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
    }

    @PutMapping("/update/image")
    public ResponseEntity<Customer> addCustomerImage(@RequestParam("email") String email, @RequestParam("image") MultipartFile imageFile) {

        Customer updatedCustomer = customerService.updateCustomerImage(email, imageFile);

        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }




}
