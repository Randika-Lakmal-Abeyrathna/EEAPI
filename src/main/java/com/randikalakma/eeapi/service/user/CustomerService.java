package com.randikalakma.eeapi.service.user;

import com.randikalakma.eeapi.exception.user.CustomerException;
import com.randikalakma.eeapi.model.Customer;
import com.randikalakma.eeapi.model.ImageData;
import com.randikalakma.eeapi.repository.CustomerRepository;
import com.randikalakma.eeapi.service.admin.ImageDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ImageDataService imageDataService;

    public Customer addCustomer(Customer customer){
        customerValidation(customer);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerByEmail(String email){
        return customerRepository.findById(email)
                .orElseThrow(()-> new CustomerException("Email +"+email+" was Not Found"));
    }

    public Customer updateCustomerImage(String email,MultipartFile imageFile){

        Customer customer = getCustomerByEmail(email);

        /// Upload Image
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(imageFile.getOriginalFilename()));

        String uploadDir= "./customer-images/";

        Path uploadPath = Paths.get(uploadDir);

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        }catch (IOException e){
            System.out.println("Cannot Create folder");
            e.printStackTrace();
        }
        Path filePath = uploadPath.resolve(fileName);
        System.out.println("FILE PATH :"+filePath.toString());
        try {
            InputStream inputStream = imageFile.getInputStream();

            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            System.out.println("Cannot upload image");
        }

        ImageData imageData = new ImageData();
        imageData.setPath(filePath.toString());

        ImageData updatedImageData = imageDataService.addImageData(imageData);

        customer.setImageData(updatedImageData);

        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer){
        customerValidation(customer);
        return customerRepository.save(customer);
    }

    private void customerValidation(Customer customer){

        String customerEmail = customer.getEmail();
        Date customerBirthDay = customer.getDateOfBirth();
        String customerFirstName =customer.getFirstName();

        if (customerEmail.isEmpty() || customerEmail.isBlank())
            throw new CustomerException("Email id cannot be empty or blank");
        if (customerRepository.existsById(customerEmail)){
            throw new CustomerException("Email id " +customer.getEmail()+" already is use.");
        }
        if (customerBirthDay.after(new Date())){
            throw new CustomerException("Invalid Date of birth");
        }
        if (customerFirstName.isBlank() || customerFirstName.isEmpty()){
            throw new CustomerException("Customer First Name cannot be empty or blank");
        }

    }
}
