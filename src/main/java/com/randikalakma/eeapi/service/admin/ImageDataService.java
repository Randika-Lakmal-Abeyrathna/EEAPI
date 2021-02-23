package com.randikalakma.eeapi.service.admin;

import com.randikalakma.eeapi.model.ImageData;
import com.randikalakma.eeapi.repository.ImageDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class ImageDataService {

    private final ImageDataRepository imageDataRepository;

    public ImageData addImageData(ImageData imageData){
        return imageDataRepository.save(imageData);
    }


}
