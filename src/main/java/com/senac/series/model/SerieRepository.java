package com.senac.series.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.series.entities.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    
    
}
