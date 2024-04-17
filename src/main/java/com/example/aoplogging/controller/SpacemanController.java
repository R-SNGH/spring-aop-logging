package com.example.aoplogging.controller;

import com.example.aoplogging.entities.Spaceman;
import com.example.aoplogging.service.SpacemanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spaceman")
public class SpacemanController {

    @Autowired
    SpacemanService service;

    //to test this Controller
    @RequestMapping("test")
    public ResponseEntity<String> testController(){
        return new ResponseEntity<>("Hello from Spaceman Controller! ", HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Spaceman> addSpaceman(@RequestBody Spaceman spaceman){
        Spaceman savedSpaceman= service.addSpaceman(spaceman);
        return new ResponseEntity<Spaceman>(savedSpaceman, HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<Spaceman>> findALlSpacemen(){
        List<Spaceman> spacemen= service.findALlSpacemen();
        return new ResponseEntity<List<Spaceman>>(spacemen, HttpStatus.OK);
    }
}
