package com.useManagement.ashok.bindings;

import lombok.Data;

import java.util.Date;
@Data
public class UserForm {
    private String firstName;
    private String lastName;
    private String phone;
   // private String password;
    private String email;
    private String mobile;
    private String gender;
    private Integer stateId;
    private Integer countryId;
    private Integer cityId;
    private Date createdDate;
    private Date updateDate;
}
