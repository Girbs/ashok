package com.useManagement.ashok.repositories;

import com.useManagement.ashok.entity.States;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepo extends JpaRepository<States, Integer> {
    List<States> findByCountryId(Integer countryId);
}
