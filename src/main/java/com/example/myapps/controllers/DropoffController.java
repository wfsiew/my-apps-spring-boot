package com.example.myapps.controllers;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.example.myapps.models.DropoffHeader;
import com.example.myapps.repositories.DropoffHeaderRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@RestController
@RequestMapping("/dropoff")
public class DropoffController {

    private static final Logger log = LoggerFactory.getLogger(DropoffController.class);

    @Autowired
    private DropoffHeaderRepository repository;

    @Autowired
    private DataSource dataSource;

    @GetMapping(value = "/list")
    public Iterable<DropoffHeader> getAll() {
        log.info("findAll");
        return repository.findAll();
    }

    @GetMapping("/pdf")
    @ResponseBody
    public void getPdf(final HttpServletResponse response) {
        final InputStream stream = this.getClass().getResourceAsStream("/reports/Shipping_Label.jasper");
        try {
            final JasperReport jasReport = (JasperReport) JRLoader.loadObject(stream);
            final String conno = "CTT01001236918";
            final Map<String, Object> p = new HashMap<>();
            p.put("consignment_no", conno);
            p.put("is_do", "false");
            p.put("work_dir", "C:\\learn\\my-apps-spring-boot\\src\\main\\resources\\reports\\");

            // Filling the report with the employee data and additional parameters
            // information.
            final JasperPrint jasperPrint = JasperFillManager.fillReport(jasReport, p, dataSource.getConnection());
            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}