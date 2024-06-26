package com.example.aoplogging.service;

import com.example.aoplogging.entities.Spaceman;
import com.example.aoplogging.repository.SpacemanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpacemanService {

    @Autowired
    SpacemanRepository repository;

    public Spaceman addSpaceman(Spaceman spaceman) throws Exception{
        if("".equals(spaceman.getName()) || spaceman.getName().isEmpty())
            throw new Exception("Please send a valid name! ");
        Spaceman savedSpaceman= repository.save(spaceman);
        return savedSpaceman;
    }

    public List<Spaceman> findALlSpacemen(){
        List<Spaceman> spacemen= repository.findAll();
        return spacemen;
    }
}
