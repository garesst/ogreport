package com.ga.api.ogreportstudio.services.managament;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRJpaDataSource;
import net.sf.jasperreports.engine.data.JsonQLDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;
import org.springframework.core.io.ByteArrayResource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ReportManagament {

    public ByteArrayResource exportReportToPDF(InputStream report,InputStream data, Map<String, Object> parameters, String selectQuery){
        try
        {
            JsonQLDataSource dataSource = new JsonQLDataSource(data,selectQuery);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            byte[] reportContent = outputStream.toByteArray();
            return new ByteArrayResource(reportContent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Ocurrio un error: "+e.toString());
            return null;
        }
    }

    public byte[] generateHTMLFromPDF(byte[] filename) throws IOException {
        PDDocument pdf = PDDocument.load(filename);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Writer output = new PrintWriter(baos);
        new PDFDomTree().writeText(pdf, output);

        return baos.toByteArray();
    }

    public byte[] exportReportToHTML(InputStream report,InputStream data, Map<String, Object> parameters, String selectQuery){
        try
        {
            HtmlExporter exporter = new HtmlExporter();
            JsonQLDataSource dataSource = new JsonQLDataSource(data,selectQuery);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            StringBuffer outputStream = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            HtmlExporterOutput y= new SimpleHtmlExporterOutput(baos);
            exporter.setExporterOutput(y);
            exporter.exportReport();
            int a=1;
            //JasperExportManager.exportReportToHtmlFile(jasperPrint);
            //JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            return baos.toByteArray();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Ocurrio un error: "+e.toString());
            return null;
        }
    }
}
