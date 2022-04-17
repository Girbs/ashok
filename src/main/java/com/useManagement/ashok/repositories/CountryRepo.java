package com.useManagement.ashok.repositories;

import com.useManagement.ashok.entity.Countries;
import com.useManagement.ashok.entity.States;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepo extends JpaRepository<Countries,Integer> {

}
