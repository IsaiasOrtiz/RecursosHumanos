/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controladoresJSF;

import com.bitlab.controladoresJPA.AbstractController;
import com.bitlab.controladoresJPA.HistorialEmpleadoController;
import com.bitlab.entidades.HistorialEmpleado;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author elcon
 */
@ManagedBean
@ViewScoped
public class HistorialControlador extends AbstractControlador<HistorialEmpleado> {

    private HistorialEmpleadoController historialController;

    public HistorialControlador() {
        super(HistorialEmpleado.class);
        historialController = new HistorialEmpleadoController();
    }

    @Override
    public void auditoria() {
        getEntidadSeleccion().setAFechaModificacion(new Date());
    }

    @Override
    public HistorialEmpleadoController getControlador() {
        return historialController;
    }

    @Override
    public boolean validacionEditar() {
        return true;
    }

}
