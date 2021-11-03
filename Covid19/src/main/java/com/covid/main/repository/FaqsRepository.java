package com.covid.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covid.main.model.FaqsModel;

@Repository
public interface FaqsRepository extends JpaRepository<FaqsModel, Integer>{

}