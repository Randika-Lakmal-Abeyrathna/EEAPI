package com.randikalakma.eeapi.service.supplier;

import com.randikalakma.eeapi.dto.SupplierRequest;
import com.randikalakma.eeapi.exception.supplier.SupplierException;
import com.randikalakma.eeapi.model.*;
import com.randikalakma.eeapi.repository.SupplierInfoRepository;
import com.randikalakma.eeapi.service.UserService;
import com.randikalakma.eeapi.service.admin.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SupplierService {


    private final PasswordEncoder passwordEncoder;
    private final CityService cityService;
    private final StatusService statusService;
    private final UserTypeService userTypeService;
    private final UserService userService;
    private final SupplierInfoRepository supplierInfoRepository;

    public SupplierInfo addSupplier(SupplierRequest supplierRequest) {
        supplierValidation(supplierRequest);
        supplierRequest.setPassword(passwordEncoder.encode(supplierRequest.getPassword()));

        City city = cityService.findCityByCityName(supplierRequest.getCity().toLowerCase());
        Status status = statusService.findStatusByStatus(supplierRequest.getStatus().toLowerCase());
        UserType userType = userTypeService.getUserTypeByUserType(supplierRequest.getUserType().toLowerCase());

        // Save User details
        User user = new User();
        user.setEmail(supplierRequest.getEmail());
        user.setAddressNo(supplierRequest.getAddressNo());
        user.setAddress_street(supplierRequest.getAddressStreet1());
        user.setAddress_street2(supplierRequest.getAddressStreet2());
        user.setContactNumber1(supplierRequest.getContactNumber1());
        user.setContactNumber2(supplierRequest.getContactNumber2());
        user.setPassword(supplierRequest.getPassword());
        user.setEnabled(false);
        user.setCity(city);
        user.setStatus(status);
        user.setUserType(userType);

        User addedUser = userService.addUser(user);

        // Save Supplier Details
        SupplierInfo supplierInfo= new SupplierInfo();
        supplierInfo.setSupplierName(supplierRequest.getSupplierName());
        supplierInfo.setCompanyName(supplierRequest.getCompanyName());
        supplierInfo.setCompanyRegNumber(supplierRequest.getCompanyRegNumber());
        supplierInfo.setUser(addedUser);

        SupplierInfo savedSupplierInfo = supplierInfoRepository.save(supplierInfo);
        userService.sendUserActivationEmail(addedUser);
        return savedSupplierInfo;
    }

    public List<SupplierInfo> getAllSupplier() {
        return supplierInfoRepository.findAll();
    }

    public SupplierInfo getSupplierByEmailAndUserType(String email){
        UserType userType = userTypeService.getUserTypeByUserType("supplier");
        User user = userService.findUserByEmailAndUserType(email, userType);
        return supplierInfoRepository.findByUser(user)
                .orElseThrow(() -> new SupplierException("Customer Info Not found"));

    }

    public SupplierInfo updateSupplier(SupplierRequest supplierRequest) {
        supplierUpdateValidation(supplierRequest);

        supplierRequest.setPassword(passwordEncoder.encode(supplierRequest.getPassword()));

        City city = cityService.findCityByCityName(supplierRequest.getCity().toLowerCase());
        Status status = statusService.findStatusByStatus(supplierRequest.getStatus().toLowerCase());
        UserType userType = userTypeService.getUserTypeByUserType(supplierRequest.getUserType().toLowerCase());

        String supplierEmail = supplierRequest.getEmail();
        User user = userService.getUserByEmail(supplierEmail);
        SupplierInfo supplierInfo = supplierInfoRepository.findByUser(user)
                .orElseThrow(()-> new SupplierException("Supplier Info not found"));

        user.setAddressNo(supplierRequest.getAddressNo());
        user.setAddress_street(supplierRequest.getAddressStreet1());
        user.setAddress_street2(supplierRequest.getAddressStreet2());
        user.setContactNumber1(supplierRequest.getContactNumber1());
        user.setContactNumber2(supplierRequest.getContactNumber2());
        user.setPassword(supplierRequest.getPassword());
        user.setCity(city);
        user.setStatus(status);
        user.setUserType(userType);

        User addedUser = userService.updateUser(user);

        supplierInfo.setSupplierName(supplierRequest.getSupplierName());
        supplierInfo.setCompanyName(supplierRequest.getCompanyName());
        supplierInfo.setCompanyRegNumber(supplierRequest.getCompanyRegNumber());
        supplierInfo.setUser(addedUser);

        return supplierInfoRepository.save(supplierInfo);
    }

    private void supplierUpdateValidation(SupplierRequest supplierRequest) {

        String supplierEmail = supplierRequest.getEmail();
        String supplierName = supplierRequest.getSupplierName();
        String supplierCompanyName = supplierRequest.getCompanyName();
        String supplierCompanyRegNumber = supplierRequest.getCompanyRegNumber();
        String password = supplierRequest.getPassword();

        if (supplierEmail.isEmpty() || supplierEmail.isBlank())
            throw new SupplierException("Email id cannot be empty or blank");
        if (supplierName.isBlank() || supplierName.isEmpty()) {
            throw new SupplierException("Supplier Name cannot be empty or blank");
        }
        if (supplierCompanyName.isBlank() || supplierCompanyName.isEmpty()) {
            throw new SupplierException("Supplier Company Name cannot be empty or blank");
        }
        if (supplierCompanyRegNumber.isBlank() || supplierCompanyRegNumber.isEmpty()) {
            throw new SupplierException("Supplier Company Registration Number cannot be empty or blank");
        }
        if (password.isBlank() || password.isEmpty()) {
            throw new SupplierException("Password cannot be empty or blank");
        }

    }

    private void supplierValidation(SupplierRequest supplierRequest) {

        String supplierEmail = supplierRequest.getEmail();
        String supplierName = supplierRequest.getSupplierName();
        String supplierCompanyName = supplierRequest.getCompanyName();
        String supplierCompanyRegNumber = supplierRequest.getCompanyRegNumber();
        String password = supplierRequest.getPassword();

        if (supplierEmail.isEmpty() || supplierEmail.isBlank())
            throw new SupplierException("Email id cannot be empty or blank");
        if (userService.checkUserExistByEmail(supplierEmail)) {
            throw new SupplierException("Email id " + supplierEmail + " already is use.");
        }
        if (supplierName.isBlank() || supplierName.isEmpty()) {
            throw new SupplierException("Supplier Name cannot be empty or blank");
        }
        if (supplierCompanyName.isBlank() || supplierCompanyName.isEmpty()) {
            throw new SupplierException("Supplier Company Name cannot be empty or blank");
        }
        if (supplierCompanyRegNumber.isBlank() || supplierCompanyRegNumber.isEmpty()) {
            throw new SupplierException("Supplier Company Registration Number cannot be empty or blank");
        }
        if (password.isBlank() || password.isEmpty()) {
            throw new SupplierException("Password cannot be empty or blank");
        }

    }

}
