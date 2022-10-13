package com.ga.api.ogreportstudio.services;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

@Service
public class Report {




    private ByteArrayResource exportReportToPDF(InputStream targetStream, Map<String, Object> parameters)
    {
        try
        {
            JasperPrint jasperPrint = JasperFillManager.fillReport(targetStream, parameters, new JREmptyDataSource());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);


            byte[] reportContent = outputStream.toByteArray();
            return new ByteArrayResource(reportContent);
        }
        catch (Exception e)
        {
            System.out.println("Ocurrio un error: "+e.toString());
            return null;
        }
    }

    private ByteArrayResource exportReportToDOCx(InputStream targetStream, Map<String, Object> parameters)
    {
        try
        {
            JasperPrint jasperPrint = JasperFillManager.fillReport(targetStream, parameters, new JREmptyDataSource());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            JRDocxExporter exporter = new JRDocxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));


            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();

            byte[] reportContent = outputStream.toByteArray();
            return new ByteArrayResource(reportContent);
        }
        catch (Exception e)
        {
            System.out.println("Ocurrio un error: "+e.toString());
            return null;
        }
    }

}
