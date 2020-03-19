package com.example.myapps.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    @Autowired
    DataSource dataSource;

    public Connection gConnection() throws SQLException, SQLTimeoutException {
        return dataSource.getConnection();
    }
}