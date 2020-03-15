package com.example.myapps.controllers;

import java.util.HashMap;
import java.util.Map;

import com.example.myapps.services.MailClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailClient mailClient;

    @GetMapping("/mail")
    public Map<String, Object> sendMail() {
        mailClient.prepareAndSend("siewwingfei@hotmail.com", "yandy mail");
        Map<String, Object> m = new HashMap<>();
        m.put("success", 1);
        return m;
    }
}