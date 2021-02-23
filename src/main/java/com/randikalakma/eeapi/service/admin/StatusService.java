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


    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    public Status getStatusById(Integer id) {
        return statusRepository.getSalutationByIdstatus(id)
                .orElseThrow(() -> new StatusException("Status by id " + id + " was not found"));
    }

    public Status addStatus(Status status) {
        statusValidation(status);
        Status newStatus = setStatusNameToLowerCase(status);
        return statusRepository.save(newStatus);
    }

    public Status updateStatus(Status status) {
        statusValidation(status);
        Status newStatus = setStatusNameToLowerCase(status);
        return statusRepository.save(newStatus);
    }

    public void deleteStatusById(Integer id) {
        statusRepository.deleteSalutationByIdstatus(id);
    }

    public List<Status> findStatusByStatus(String status) {
        return statusRepository.findStatusByStatus(status);
    }

    private void statusValidation(Status status){
        String statusName = status.getStatus().toLowerCase();
        if (findStatusByStatus(statusName).size()>0){
            throw new StatusException("Status "+statusName+" is already in use");
        }else if(statusName.isBlank()|| statusName.isEmpty()){
            throw new StatusException("Status Can not be empty or blank");
        }
    }

    private Status setStatusNameToLowerCase(Status status){
        status.setStatus(status.getStatus().toLowerCase());
        return status;
    }

}
