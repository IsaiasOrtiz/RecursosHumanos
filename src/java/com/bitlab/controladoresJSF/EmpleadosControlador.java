/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controladoresJSF;

import com.bitlab.controladoresJPA.AreaController;
import com.bitlab.controladoresJPA.EmpleadoController;
import com.bitlab.controladoresJPA.EstadoController;
import com.bitlab.controladoresJPA.HistorialEmpleadoController;
import com.bitlab.entidades.Empleado;
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
public class EmpleadosControlador extends AbstractControlador<Empleado> {

    private EmpleadoController empleadoController;
    private EstadoController estadoController;
    private AreaController departamentoController;

    public EmpleadosControlador() {
        super(Empleado.class);
        empleadoController = new EmpleadoController();
        estadoController = new EstadoController();
        departamentoController = new AreaController();
    }

    @Override
    public EmpleadoController getControlador() {
        return empleadoController;
    }

    @Override
    public void historial() {
        HistorialEmpleadoController h = new HistorialEmpleadoController();
        HistorialEmpleado historia = new HistorialEmpleado();
        historia.setEpId(getEntidadSeleccion());
        if (null != getEntidadSeleccion().getEsId().getEsId()) {
            switch (getEntidadSeleccion().getEsId().getEsId()) {
                case 1:
                    historia.setHtpDescripcion("El empleado esta laborando " + new Date());
                    h.crear(historia);
                    break;
                case 2:
                    historia.setHtpDescripcion("Se despidio el empleado: " + new Date());
                    h.crear(historia);
                    break;
                case 3:
                    historia.setHtpDescripcion("El empleado renuncio: " + new Date());
                    h.crear(historia);
                    break;
                default:
                    break;
            }
        }

    }

    public EstadoController getEstadoController() {
        return estadoController;
    }

    public AreaController getDepartamentoController() {
        return departamentoController;
    }

    @Override
    public void auditoria() {
        getEntidadSeleccion().setAFechaModificacion(new Date());
    }

}
