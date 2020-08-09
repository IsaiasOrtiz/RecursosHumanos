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
import com.bitlab.utilidades.UtilidadesWeb;
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

    private final String ADVERTENCIA = "Advertencia!";
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

    @Override
    public boolean validacionEditar() {
        boolean retornoEp = true;
        if (getEntidadSeleccion().getEpNombres().isEmpty()) {
            UtilidadesWeb.mensajeAdvertencia(ADVERTENCIA, "El campo nombres no puede quedar vacio");
            retornoEp = false;
        }
        if (getEntidadSeleccion().getEpApellidos().isEmpty()) {
            UtilidadesWeb.mensajeAdvertencia(ADVERTENCIA, "El campo apellidos no puede quedar vacio");
            retornoEp = false;
        }
        if (getEntidadSeleccion().getEpDireccion().isEmpty()) {
            UtilidadesWeb.mensajeAdvertencia(ADVERTENCIA, "El campo direccion no puede quedar vacio");
            retornoEp = false;
        }
        try {
            if (getEntidadSeleccion().getEpFechaNacimiento() == null) {
                UtilidadesWeb.mensajeAdvertencia(ADVERTENCIA, "El campo feecha de nacimiento no puede quedar vacio");
                retornoEp = false;
            }
        } catch (Exception e) {
            UtilidadesWeb.mensajeAdvertencia(ADVERTENCIA, "Al perecer el formato de fecha no es valido");
            retornoEp = false;
        }
        if (getEntidadSeleccion().getEpDui().isEmpty() && getEntidadSeleccion().getEpDui().length() >= 9) {
            UtilidadesWeb.mensajeAdvertencia(ADVERTENCIA, "Al parecer el DUI no cuenta con los digitos necesarios ");
            retornoEp = false;
        }
        if (getEntidadSeleccion().getEpSexo().isEmpty()) {
            UtilidadesWeb.mensajeAdvertencia(ADVERTENCIA, "El campo sexo no puede quedar vacio");
            retornoEp = false;
        } else if (!(getEntidadSeleccion().getEpSexo().equals("M") || getEntidadSeleccion().getEpSexo().equals("F"))) {
            UtilidadesWeb.mensajeAdvertencia(ADVERTENCIA, "El sexo debe ser 'M' o 'F'");
            retornoEp = false;
        }
        if (getEntidadSeleccion().getEpSalario() < 300) {
            UtilidadesWeb.mensajeAdvertencia(ADVERTENCIA, "El salario no puede ser menos a $300");
        }
        return retornoEp;
    }

    @Override
    public boolean validacionGuardar() {
        return validacionEditar();
    }

}
