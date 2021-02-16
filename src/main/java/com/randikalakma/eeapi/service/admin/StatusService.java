package com.randikalakma.eeapi.service.admin;

import com.randikalakma.eeapi.exception.admin.StatusException;
import com.randikalakma.eeapi.model.Status;
import com.randikalakma.eeapi.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class StatusService {

    private final StatusRepository statusRepository;


    public List<Status> getAllStatus(){
        return statusRepository.findAll();
    }

    public Status getStatusById(Integer id){
        return statusRepository.getSalutationByIdstatus(id)
                .orElseThrow(()->new StatusException("Status by id " +id+" was not found"));
    }

    public Status addStatus(Status status){
        return statusRepository.save(status);
    }

    public Status updateStatus(Status status){
        return statusRepository.save(status);
    }

    public void deleteStatusById(Integer id){
        statusRepository.deleteSalutationByIdstatus(id);
    }

}
