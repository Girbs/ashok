package com.useManagement.ashok.repositories;

import com.useManagement.ashok.entity.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepo extends JpaRepository<Cities, Integer> {
    List<Cities> findByStateId(Integer stateId);
}
