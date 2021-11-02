package com.covid.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covid.main.model.PreventionModel;

@Repository
public interface PreventionRepository extends JpaRepository<PreventionModel, Integer>{

}