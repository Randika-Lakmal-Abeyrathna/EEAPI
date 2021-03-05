package com.randikalakma.eeapi.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequest extends UserRequest {

    private String supplierName;
    private String companyName;
    private String companyRegNumber;

}
