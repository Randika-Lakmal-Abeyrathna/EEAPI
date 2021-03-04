package com.randikalakma.eeapi.controller.supplier;

import com.randikalakma.eeapi.dto.SupplierRequest;
import com.randikalakma.eeapi.model.SupplierInfo;
import com.randikalakma.eeapi.service.UserService;
import com.randikalakma.eeapi.service.supplier.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/supplier/supplier")
@AllArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;
    private final UserService userService;


    @PostMapping("/add")
    public ResponseEntity<SupplierInfo> addSupplier(@RequestBody SupplierRequest supplierRequest){
        SupplierInfo supplierInfo =supplierService.addSupplier(supplierRequest);
        return new ResponseEntity<>(supplierInfo,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<SupplierInfo> updateSupplier(@RequestBody SupplierRequest supplier){
        SupplierInfo supplierInfo =supplierService.updateSupplier(supplier);
        return new ResponseEntity<>(supplierInfo,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SupplierInfo>> getAllSuppliers(){
        List<SupplierInfo> supplierList = supplierService.getAllSupplier();
        return new ResponseEntity<>(supplierList,HttpStatus.OK);
    }

    @PostMapping("/find/email")
    public ResponseEntity<SupplierInfo> getSupplierByEmail(@RequestParam("email") String email){
        SupplierInfo supplier = supplierService.getSupplierByEmailAndUserType(email);
        return new ResponseEntity<>(supplier,HttpStatus.OK);
    }

    @PutMapping("/update/image")
    public ResponseEntity<?> addSupplierImage(@RequestParam("email") String email, @RequestParam("image") MultipartFile imageFile) {
        userService.updateUserImage(email, imageFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
