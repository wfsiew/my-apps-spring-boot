package com.example.myapps.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import com.example.myapps.models.DropoffHeader;

public interface DropoffHeaderRepository extends CrudRepository<DropoffHeader, UUID> {
    
    List<DropoffHeader> findByDropoffNo(String dropoffNo);
}