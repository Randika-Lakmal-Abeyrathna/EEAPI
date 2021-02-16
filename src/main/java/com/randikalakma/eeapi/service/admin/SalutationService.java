package com.randikalakma.eeapi.service.admin;

import com.randikalakma.eeapi.exception.admin.SalutationException;
import com.randikalakma.eeapi.model.Salutation;
import com.randikalakma.eeapi.repository.SalutationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SalutationService {

    private final SalutationRepository salutationRepository;


    public List<Salutation> findAllSalutations(){
        return salutationRepository.findAll();
    }

    public Salutation findById(Integer id){
        return salutationRepository.getSalutationByIdsalutation(id)
                .orElseThrow(()-> new SalutationException("Salutation by Id "+id+" was not found"));
    }

    public Salutation addSalutation(Salutation salutation){
        return salutationRepository.save(salutation);
    }

    public Salutation updateSalutation(Salutation salutation){
        return salutationRepository.save(salutation);
    }

    public void deleteSalutationById(Integer id){
        salutationRepository.deleteCityByIdsalutation(id);
    }


}
