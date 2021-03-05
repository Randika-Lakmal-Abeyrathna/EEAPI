package com.randikalakma.eeapi.service;

import com.randikalakma.eeapi.exception.UserException;
import com.randikalakma.eeapi.exception.user.CustomerException;
import com.randikalakma.eeapi.model.*;
import com.randikalakma.eeapi.repository.ImageDataRepository;
import com.randikalakma.eeapi.repository.UserRepository;
import com.randikalakma.eeapi.repository.UserTokenRepository;
import com.randikalakma.eeapi.service.admin.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ImageDataRepository imageDataRepository;
    private final UserTokenRepository userTokenRepository;
    private final MailService mailService;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public User findUserByEmailAndUserType(String email, UserType userType){
       return userRepository.findByEmailAndUserType(email,userType)
                .orElseThrow(()-> new CustomerException("User Not Found"));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public boolean checkUserExistByEmail(String email){
        return userRepository.existsById(email);
    }

    public User getUserByEmail(String email){
        return userRepository.findById(email)
                .orElseThrow(()-> new UserException("User Not found with email "+email));
    }

    public void updateUserImage(String email, MultipartFile file){
        User user = getUserByEmail(email);

        // Upload Image start

        String getCurrentDateAndTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); // Using because same image name can upload
        System.out.println(getCurrentDateAndTime);
        String fileName= StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileNameFront = fileName.split("\\.")[0];
        String fileExtension=fileName.split("\\.")[1];
        fileNameFront +=getCurrentDateAndTime;
        fileName = fileNameFront+"."+fileExtension;
        System.out.println("File Name"+fileName);
        String uploadDir = "./user-images/";
        Path uploadPath = Paths.get(uploadDir);

        try{
            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
        }catch(IOException exception){
            System.out.println("cannot create the user image folder");
            exception.printStackTrace();
        }

        Path filePath = uploadPath.resolve(fileName);
        System.out.println("FILE PATH :" + filePath.toString());

        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageData imageData = new ImageData();
        imageData.setPath(filePath.toString());

        ImageData uploadedImage =imageDataRepository.save(imageData);

        user.setImageData(uploadedImage);
        userRepository.save(user);
    }

    public void sendUserActivationEmail(User user){
        String userToken = generateCustomerVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please Activate your account", user.getEmail(),
                "Thank you for signing up ! Please click on the below url to activate your account : " +
                        "http://localhost:8080/api/user/accountverification/" + userToken));
    }

    private String generateCustomerVerificationToken(User user) {

        String customerVerificationToken = UUID.randomUUID().toString();

        UserToken userToken = new UserToken();
        userToken.setToken(customerVerificationToken);
        userToken.setUser(user);

        userTokenRepository.save(userToken);
        return customerVerificationToken;
    }

    public void activateUser(String token){
        Optional<UserToken> userToken = userTokenRepository.findByToken(token);
        userToken.orElseThrow(()->new UserException("Invalid Token"));

        String userEmail = userToken.get().getUser().getEmail();
        User user = getUserByEmail(userEmail);
        user.setEnabled(true);
        userRepository.save(user);
        deleteUserToken(token,user);
    }

    private void deleteUserToken(String token,User user){
        userTokenRepository.deleteUserTokenByTokenAndUser(token,user);
    }






}
