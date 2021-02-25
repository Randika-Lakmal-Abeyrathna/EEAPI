package com.randikalakma.eeapi.controller.supplier;

import com.randikalakma.eeapi.model.Customer;
import com.randikalakma.eeapi.model.Supplier;
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


    @PostMapping("/add")
    public ResponseEntity<?> addSupplier(@RequestBody Supplier supplier){
        supplierService.addSupplier(supplier);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier supplier){
        Supplier updatedSupplier =supplierService.updateSupplier(supplier);
        return new ResponseEntity<>(updatedSupplier,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Supplier>> getAllSuppliers(){
        List<Supplier> supplierList = supplierService.getAllSupplier();
        return new ResponseEntity<>(supplierList,HttpStatus.OK);
    }

    @PostMapping("/find/email")
    public ResponseEntity<Supplier> getSupplierByEmail(@RequestParam("email") String email){
        Supplier supplier = supplierService.getSupplierByEmail(email);
        return new ResponseEntity<>(supplier,HttpStatus.OK);
    }

    @PutMapping("/update/image")
    public ResponseEntity<Supplier> addSupplierImage(@RequestParam("email") String email, @RequestParam("image") MultipartFile imageFile) {
        Supplier updateSupplier = supplierService.updateSupplierImage(email,imageFile);

        return new ResponseEntity<>(updateSupplier, HttpStatus.OK);
    }

    @GetMapping("/accountverification/{token}")
    public ResponseEntity<?> enableSupplier(@PathVariable("token") String token){
        supplierService.activateSupplier(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
