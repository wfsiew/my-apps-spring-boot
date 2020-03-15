package com.example.myapps.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.myapps.repositories.DropoffHeaderRepository;
import com.example.myapps.models.DropoffHeader;

@RestController
public class DropoffController {

    private static final Logger log = LoggerFactory.getLogger(DropoffController.class);

	@Autowired
    private DropoffHeaderRepository repository;
    
    @GetMapping(value="/dropoff/list")
	public Iterable<DropoffHeader> getAll() {
        log.info("findAll");
		return repository.findAll();
	}
}