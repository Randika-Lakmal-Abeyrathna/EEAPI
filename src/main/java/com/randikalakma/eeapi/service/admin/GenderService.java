package com.randikalakma.eeapi.service.admin;

import com.randikalakma.eeapi.model.Gender;
import com.randikalakma.eeapi.repository.GenderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class GenderService {

    private final GenderRepository genderRepository;

    public List<Gender> getAllGender(){
        return genderRepository.findAll();
    }

    public Gender addGender(Gender gender){
        return genderRepository.save(gender);
    }

}
