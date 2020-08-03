/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controladoresJSF;

import com.bitlab.controladoresJPA.EstadoController;
import com.bitlab.entidades.Estado;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author elcon
 */
@ManagedBean
@ViewScoped
public class EstadoControlador extends AbstractControlador<Estado> {

    private EstadoController estadoController;

    public EstadoControlador() {
        super(Estado.class);
        estadoController = new EstadoController();
    }

    /**
     * Creates a new instance of EstadoControlador
     */
    @Override
    public EstadoController getControlador() {
        return estadoController;
    }

    @Override
    public void auditoria() {
        getEntidadSeleccion().setAFechaModificacion(new Date());

    }

}
