package com.randikalakma.eeapi.controller.admin;

import com.randikalakma.eeapi.model.Salutation;
import com.randikalakma.eeapi.service.admin.SalutationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/salutation")
@AllArgsConstructor
public class SalutationController {

    private final SalutationService salutationService;

    @GetMapping("/all")
    public ResponseEntity<List<Salutation>> getAllSalutations(){
        List<Salutation> salutationList = salutationService.findAllSalutations();
        return new ResponseEntity<>(salutationList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Salutation> getSalutationById(@PathVariable Integer id){
        Salutation salutation = salutationService.findById(id);
        return new ResponseEntity<>(salutation,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Salutation> addSalutation(@RequestBody Salutation salutation){
        Salutation newSalutation = salutationService.addSalutation(salutation);
        return new ResponseEntity<>(newSalutation,HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Salutation> updateSalutation(@RequestBody Salutation salutation){
        Salutation newSalutation = salutationService.updateSalutation(salutation);
        return new ResponseEntity<>(newSalutation,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSalutationById(@PathVariable Integer id){
        salutationService.deleteSalutationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
