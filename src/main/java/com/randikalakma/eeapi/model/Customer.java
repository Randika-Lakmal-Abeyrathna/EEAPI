package com.randikalakma.eeapi.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer{

    @Id
    @NotNull
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String addressNo;
    private String addressStreet;
    private String addressStreet2;
    private String nicNumber;
    private Date dateOfBirth;
    private Integer contactNumber1;
    private Integer contactNumber2;
    private String password;
    private boolean enabled;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_idcity",referencedColumnName = "idcity")
    private City city;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_idgender",referencedColumnName = "idgender")
    private Gender gender;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salutation_idsalutation",referencedColumnName = "idsalutation")
    private Salutation salutation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_idstatus",referencedColumnName = "idstatus")
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_data_idimage_data" ,referencedColumnName = "idimage_data")
    private ImageData imageData;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_type_iduser_type",referencedColumnName = "iduser_type")
    private UserType userType;





}
