/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controladoresJSF;

import com.bitlab.utilidades.Reportes;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author elcon
 */
@ManagedBean
@ViewScoped
public class ReportesControlador implements Serializable {

    private static final String PDF = "PDF";

    public ReportesControlador() {
    }

    Reportes reportes = new Reportes();

    public void reporteEmpleados() {
        try {
            reportes.generarReporteUsuarios("/reportes/empleados.jasper", "Reporte de empleados", PDF);
        } catch (SQLException ex) {
            Logger.getLogger(ReportesControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportesControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reporteUsuarios() {
        try {
            reportes.generarReporteUsuarios("/reportes/reportedeUsuarios.jasper", "Reporte de usuarios", PDF);
        } catch (SQLException ex) {
            Logger.getLogger(ReportesControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportesControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reportePagos() {
        try {
            reportes.generarReporteUsuarios("/reportes/pagos.jasper", "Reporte de pagos", PDF);
        } catch (SQLException ex) {
            Logger.getLogger(ReportesControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportesControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reporteDepartamentos() {
        try {
            try {
                reportes.generarReporteUsuarios("/reportes/reporteDepartamentos.jasper", "Reporte de departamentos", PDF);
            } catch (IOException ex) {
                Logger.getLogger(ReportesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportesControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reporteDePlanilla() {
        try {
            reportes.generarReporteUsuarios("/reportes/excelPagos.jasper", "Reporte de departamentos", "EXCEL");
        } catch (SQLException ex) {
            Logger.getLogger(ReportesControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportesControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
