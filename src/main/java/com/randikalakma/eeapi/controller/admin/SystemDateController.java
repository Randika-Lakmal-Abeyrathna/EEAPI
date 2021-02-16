package com.randikalakma.eeapi.controller.admin;

import com.randikalakma.eeapi.model.SystemDate;
import com.randikalakma.eeapi.service.admin.SystemDateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/systemdate")
@AllArgsConstructor
public class SystemDateController {

    private final SystemDateService systemDateService;


    @GetMapping("/all")
    public ResponseEntity<List<SystemDate>> getAllSystemDates(){
        List<SystemDate> systemDateList = systemDateService.getAllSystemDates();
        return new ResponseEntity<>(systemDateList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SystemDate> getSystemDateById(@PathVariable Integer id){
        SystemDate systemDate = systemDateService.getSystemDateById(id);
        return new ResponseEntity<>(systemDate,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<SystemDate> addSystemDate(@RequestBody SystemDate systemDate){
        SystemDate newSystemDate = systemDateService.addSystemDate(systemDate);
        return new ResponseEntity<>(newSystemDate,HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<SystemDate> updateSystemDate(@RequestBody SystemDate systemDate){
        SystemDate newSystemDate = systemDateService.updateSystemDate(systemDate);
        return new ResponseEntity<>(newSystemDate,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSystemDateById(@PathVariable Integer id){
        systemDateService.deleteSystemDateById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
