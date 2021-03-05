package com.randikalakma.eeapi.service.admin;

import com.randikalakma.eeapi.exception.admin.StatusException;
import com.randikalakma.eeapi.model.UserType;
import com.randikalakma.eeapi.repository.UserTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserTypeService {

    private final UserTypeRepository userTypeRepository;

    public UserType getUserTypeByUserType(String UserType){
        return userTypeRepository.findUserTypeByUserType(UserType)
                .orElseThrow(() -> new StatusException("Status by id " + UserType + " was not found"));
    }

}
