package com.senac.series.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.series.entities.Serie;
import com.senac.series.model.SerieRepository;

@Service
public class SerieService {
    
    @Autowired
    private SerieRepository repository;

    public List<Serie> findAll(){
        return repository.findAll();
    }

    public Serie insert(Serie obj) {
        return repository.save(obj);
      }

}
