/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controladoresJSF;

import com.bitlab.beans.Planilla;
import com.bitlab.controladoresJPA.EmpleadoController;
import com.bitlab.controladoresJPA.HistorialEmpleadoController;
import com.bitlab.controladoresJPA.PagosController;
import com.bitlab.entidades.Empleado;
import com.bitlab.entidades.HistorialEmpleado;
import com.bitlab.entidades.Pagos;
import com.bitlab.utilidades.PlanillaUtilidad;
import com.bitlab.utilidades.UtilidadesWeb;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class PlanillaControlador {

    private List<Planilla> planilla;

    /**
     * Creates a new instance of PlanillaControlador
     */
    public PlanillaControlador() {

    }
    /**
     * Genera la planilla acorde a los salarios de los empleados
     * en forma mensual
     * @return 
     */
    public List<Planilla> getPlanilla() {
        EmpleadoController em = new EmpleadoController();
        PlanillaUtilidad pl = new PlanillaUtilidad();
        planilla = new ArrayList<Planilla>();
        Planilla p;
        planilla.clear();
        List<Empleado> empleado = em.encontrarTodos();
        for (Empleado e : empleado) {
            if (e.getEsId().getEsId() == 1) {
                p = new Planilla();
                p.setFecha(new Date());
                p.setIdEmpleado(e.getEpId());
                p.setNombreEmpleado(e.getEpNombres() + " " + e.getEpApellidos());
                p.setMonto(pl.sueldoNeto(e.getEpSalario()));
                p.setSalario(e.getEpSalario());
                planilla.add(p);
            }

        }
        return planilla;
    }
    /**
     * Con este metodo hacemos el pago de la planilla para todos los
     * empleados.
     */
    public void realizarPago() {
        EmpleadoController em = new EmpleadoController();
        PlanillaUtilidad pl = new PlanillaUtilidad();
        PagosController pagar = new PagosController();
        Pagos pagos;
        List<Empleado> empleado = em.encontrarTodos();
        byte cont = 0;
        for (Pagos ph : pagar.encontrarTodos()) {
            if (ph.getPgFecha().getMonth() == (new Date()).getMonth() && ph.getPgFecha().getYear() == (new Date()).getYear()) {
                cont = 10;
            }
        }
        if (cont == 0) {
            HistorialEmpleadoController hi = new HistorialEmpleadoController();
            HistorialEmpleado newH = new HistorialEmpleado();
            for (Empleado e : empleado) {
                if (e.getEsId().getEsId() == 1) {
                    pagos = new Pagos();
                    pagos.setPgFecha(new Date());
                    pagos.setEpId(e);
                    pagos.setPgMonto(pl.sueldoNeto(e.getEpSalario()));
                    pagos.setAFechaCreacion(new Date());
                    pagar.crear(pagos);
                    newH.setHtpDescripcion("Se le pago al empleado " + e.getEpNombres() + " " + e.getEpApellidos() + "El " + new Date());
                    newH.setEpId(e);
                    hi.crear(newH);
                }
            }
            UtilidadesWeb.mensajeInfo("Exito", "Usted realizo los pagos del mes");
        } else {
            UtilidadesWeb.mensajeAdvertencia("Pagos", "Los pagos ya se realizaron este mes");
        }

    }
    /**
     * Consulta el estado de los pagos mensuales
     * en caso de estar pendiente lo pone como 
     * pendiente de pago.
     * @return 
     */
    public String estadoDePago() {
        PagosController pagar = new PagosController();
        for (Pagos ph : pagar.encontrarTodos()) {
            if (ph.getPgFecha().getMonth() == (new Date()).getMonth() && ph.getPgFecha().getYear() == (new Date()).getYear()) {
                return "Pagado";
            }
        }
        return "Pendiente de pago";
    }
}
