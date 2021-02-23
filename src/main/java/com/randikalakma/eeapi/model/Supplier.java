package com.randikalakma.eeapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Supplier {

    @Id
    private String email;
    private String supplierName;
    private String addressNo;
    private String addressStreet;
    private String addressStreet2;
    private Integer contactNumber;
    private Integer contactNumber2;
    private String companyName;
    private String companyRegNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_idcity",referencedColumnName = "idcity")
    private City city;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_idstatus",referencedColumnName = "idstatus")
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_type_iduser_type",referencedColumnName = "iduser_type")
    private UserType userType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_data_idimage_data",referencedColumnName = "idimage_data")
    private ImageData imageData;


}
