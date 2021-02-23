package com.randikalakma.eeapi.service.supplier;

import com.randikalakma.eeapi.exception.supplier.SupplierException;
import com.randikalakma.eeapi.model.ImageData;
import com.randikalakma.eeapi.model.Supplier;
import com.randikalakma.eeapi.repository.SupplierRepository;
import com.randikalakma.eeapi.service.admin.ImageDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ImageDataService imageDataService;

    public Supplier addSupplier(Supplier supplier){
        supplierValidation(supplier);
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSupplier(){
        return supplierRepository.findAll();
    }

    public Supplier getSupplierByEmail(String email){
        return supplierRepository.findById(email)
                .orElseThrow(()-> new SupplierException("Email "+email+" was not found"));
    }

    public Supplier updateSupplier(Supplier supplier){
        supplierValidation(supplier);
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplierImage(String email, MultipartFile imageFile){
        Supplier supplier = getSupplierByEmail(email);

        /// Upload Image
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(imageFile.getOriginalFilename()));

        String uploadDir= "./supplier-images/";

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

        supplier.setImageData(updatedImageData);
        return supplierRepository.save(supplier);
    }

    private void supplierValidation(Supplier supplier){

        String supplierEmail = supplier.getEmail();
        String supplierName = supplier.getSupplierName();
        String supplierCompanyName = supplier.getCompanyName();
        String supplierCompanyRegNumber = supplier.getCompanyRegNumber();

        if (supplierEmail.isEmpty() || supplierEmail.isBlank())
            throw new SupplierException("Email id cannot be empty or blank");
        if (supplierRepository.existsById(supplierEmail)){
            throw new SupplierException("Email id " +supplierEmail+" already is use.");
        }
        if (supplierName.isBlank() || supplierName.isEmpty()){
            throw new SupplierException("Supplier Name cannot be empty or blank");
        }
        if (supplierCompanyName.isBlank() || supplierCompanyName.isEmpty()){
            throw new SupplierException("Supplier Company Name cannot be empty or blank");
        }
        if (supplierCompanyRegNumber.isBlank() || supplierCompanyRegNumber.isEmpty()){
            throw new SupplierException("Supplier Company Registration Number cannot be empty or blank");
        }

    }

}
