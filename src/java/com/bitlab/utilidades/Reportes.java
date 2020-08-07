/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;

import com.bitlab.propiedades.Propiedades;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

/**
 *
 * @author elcon
 */
public class Reportes {

    private static final String EXCELE = ".xls";
    JasperPrint jasperPrint;
    HttpServletResponse httpServletResponse = null;
    private int bit;

    /**
     * Recibe el directorio en el cual se encuentra el reporte para ser
     * ejecutado.(dir/reporte.jasper) y el nombre con el cual se generara el
     * pdf, tambien al mandarle como parametro EXCEL genera en exel el pdf
     *
     * @param directorio
     * @param nombre
     * @param tipo
     */
    public void generarReporteUsuarios(String directorio, String nombre, String tipo) throws SQLException, IOException {
        System.setProperty("java.awt.headless", "true");
        Connection con = null;
        String propiedades = "config.properties";
        Encryptacion encriptacion = new Encryptacion();
        Properties pro = new Properties();
        ServletOutputStream servletOutputStream;
       
       

        try {
            pro.load(Propiedades.flujoDeDatos(propiedades));
            String valor1 = encriptacion.getDesencryptacion((String) pro.get("bu"));
            String valor2 = encriptacion.getDesencryptacion((String) pro.get("bps"));
            String valor3 = encriptacion.getDesencryptacion((String) pro.get("str"));

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(valor3, valor1, valor2);
            } catch (Exception ex) {

            }
            ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String reportPath = sc.getRealPath(directorio);
            Map<String, Object> parametros = new HashMap<>();
            try {
                jasperPrint = JasperFillManager.fillReport(reportPath, parametros, con);
            } catch (JRException ex) {

            }
            httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            if ("EXCEL".equals(tipo)) {
                File f;
                InputStream in;
                JRXlsExporter exporter = new JRXlsExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nombre + EXCELE);
                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
//                exporter.exportReport ();
                f = new File(nombre + EXCELE);
                httpServletResponse.setContentType("application/vnd.ms-excel");
                httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + nombre + EXCELE);
               
                 try {
                servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                in = new FileInputStream(f);
                bit = 256;
                while ((bit) >= 0) {
                    bit = in.read();
                    servletOutputStream.write(bit);
                }

                servletOutputStream.flush();
                servletOutputStream.close();
                in.close();
            } catch (Exception ex) {
            }
                
            } else if ("PDF".equals(tipo)) {
                httpServletResponse.setContentType("application/pdf");
                httpServletResponse.setHeader("Content-Disposition", "inline; filename=" + nombre + ".pdf");
                try {
                    servletOutputStream = httpServletResponse.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                } catch (Exception ex) {
                }
            }
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException ex) {

        } finally {
            if (!con.isClosed()) {
                con.close();
            }
            if (con == null) {
                con.close();
            }

        }
    }
}
