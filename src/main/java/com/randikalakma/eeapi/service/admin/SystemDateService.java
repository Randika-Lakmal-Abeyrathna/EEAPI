package com.randikalakma.eeapi.service.admin;

import com.randikalakma.eeapi.exception.admin.SystemDateException;
import com.randikalakma.eeapi.model.SystemDate;
import com.randikalakma.eeapi.repository.SystemDateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SystemDateService {

    private final SystemDateRepository systemDateRepository;

    public List<SystemDate> getAllSystemDates(){
        return systemDateRepository.findAll();
    }

    public SystemDate getSystemDateById(Integer id){
        return systemDateRepository.getSystemDateByidsystemDate(id)
                .orElseThrow(()->new SystemDateException("System date by id "+id+" was not found"));
    }

    public SystemDate addSystemDate(SystemDate systemDate){
        return systemDateRepository.save(systemDate);
    }

    public SystemDate updateSystemDate(SystemDate systemDate){
        return systemDateRepository.save(systemDate);
    }

    public void deleteSystemDateById(Integer id){
        systemDateRepository.deleteSystemDateByidsystemDate(id);
    }

}
