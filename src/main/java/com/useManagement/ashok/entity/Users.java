package com.useManagement.ashok.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="Users")
public class Users implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private String email;
    private String mobile;
    private String gender;
    private Integer stateId;
    private Integer countryId;
    private Integer cityId;
    private Date createdDate;
    private Date updateDate;
    private String status;
}
