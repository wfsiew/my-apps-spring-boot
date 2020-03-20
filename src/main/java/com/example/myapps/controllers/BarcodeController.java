package com.example.myapps.controllers;

import java.awt.image.BufferedImage;

import com.example.myapps.services.BarcodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barcode")
public class BarcodeController {

    @Autowired
    BarcodeService barcodeService;

    @GetMapping(value = "/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> generateCode128BarcodeImage(@PathVariable("barcode") String barcode)
    throws Exception {
        BufferedImage b = barcodeService.generateCode128BarcodeImage(barcode);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @GetMapping(value = "/okapi/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> generateCode128BarcodeImageOkapi(@PathVariable("barcode") String barcode)
    throws Exception {
        BufferedImage b = barcodeService.generateCode128BarcodeImageOkapi(barcode);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
}