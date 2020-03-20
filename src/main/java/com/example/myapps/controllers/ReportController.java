package com.example.myapps.controllers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.InputStreamResource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import io.woo.htmltopdf.HtmlToPdf;
import io.woo.htmltopdf.HtmlToPdfObject;

import com.example.myapps.models.Greeting;
import com.example.myapps.services.BarcodeService;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private BarcodeService barcodeService;

    @GetMapping("/pdf")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getneratePDF() throws Exception {
        try {
            final Context context = new Context(Locale.getDefault());
            final List<Greeting> ls = new ArrayList<>();
            final Greeting g1 = new Greeting(1, "Good Morning");
            final Greeting g2 = new Greeting(2, "Good Night");
            ls.add(g1);
            ls.add(g2);

            String barcode = barcodeService.getBase64Image(barcodeService.generateCode128BarcodeImageOkapi("CIT01000896"));

            context.setVariable("name", "ken");
            context.setVariable("greetingList", ls);
            context.setVariable("barcode", barcode);
            final String h = templateEngine.process("report.html", context);
            final InputStream in = HtmlToPdf.create()
            .object(HtmlToPdfObject.forHtml(h))
            .convert();

            final HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=report.pdf");

            return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(in));
        }
        
        catch (Exception e) {
            e.printStackTrace();
            throw(e);
        }
    }

    /* private byte[] getByteArray(InputStream in) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] b = null;

        try {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = in.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
        
            buffer.flush();
            b = buffer.toByteArray();
        }
        
        catch (Exception e) {

        }

        return b;
    } */
}