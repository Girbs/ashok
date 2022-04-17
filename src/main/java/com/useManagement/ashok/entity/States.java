package com.useManagement.ashok.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="States")
public class States implements Serializable {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String stateName;
    private Integer countryId;
}