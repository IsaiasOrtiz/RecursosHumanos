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
import com.bitlab.entidades.Area;
import com.bitlab.entidades.Empleado;
import com.bitlab.entidades.Estado;
import com.bitlab.entidades.HistorialEmpleado;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author elcon
 */
@ManagedBean
@ViewScoped
public class EmpleadosControlador extends AbstractControlador<Empleado> {

    private EmpleadoController empleadoController;
    private String departamentoJoin;
    private String estadoJoin;
    private List<SelectItem> departamento;
    private List<SelectItem> estado;
    private List<SelectItem> sexo;

    public EmpleadosControlador() {
        super(Empleado.class);
        empleadoController = new EmpleadoController();
    }

    public List<SelectItem> getSexo() {
        sexo = new ArrayList<SelectItem>();
        sexo.clear();
        SelectItem item = new SelectItem("Masculino");
        sexo.add(item);
        item = new SelectItem("Femenino");
        sexo.add(item);
        return sexo;
    }

    public List<SelectItem> getDepartamento() {
        this.departamento = new ArrayList<SelectItem>();
        AreaController sx = new AreaController();
        List<Area> list = sx.encontrarTodos();
        departamento.clear();
        for (Area e : list) {
            SelectItem item = new SelectItem(e.getDpId() + "-" + e.getDpNombre());
            departamento.add(item);
        }
        return departamento;
    }

    public List<SelectItem> getEstado() {
        this.estado = new ArrayList<SelectItem>();
        EstadoController sx = new EstadoController();
        List<Estado> list = sx.encontrarTodos();
        estado.clear();
        for (Estado e : list) {
            SelectItem item = new SelectItem(e.getEsId() + "-" + e.getEsNombre());
            estado.add(item);
        }
        return estado;
    }

    @Override
    public EmpleadoController getControlador() {
        return empleadoController;
    }

    public String getDepartamentoJoin() {
        return departamentoJoin;
    }

    public void setDepartamentoJoin(String departamentoJoin) {
        this.departamentoJoin = departamentoJoin;
    }

    public String getEstadoJoin() {
        return estadoJoin;
    }

    public void setEstadoJoin(String estadoJoin) {
        this.estadoJoin = estadoJoin;
    }

    @Override
    public void join() {
        estadoJoin = estadoJoin.replaceAll("\\D+", "");
        departamentoJoin = departamentoJoin.replaceAll("\\D+", "");
        try {
            Estado est;
            Area ar;
            AreaController arc = new AreaController();
            EstadoController estc = new EstadoController();
            int estadoObj = Integer.parseInt(estadoJoin);
            int areaObj = Integer.parseInt(departamentoJoin);
            est = estc.buscarObject(estadoObj);
            ar = arc.buscarObject(areaObj);
            getEntidadSeleccion().setEsId(est);
            getEntidadSeleccion().setDpId(ar);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadosControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void historial() {
        HistorialEmpleadoController h = new HistorialEmpleadoController();
        HistorialEmpleado historia = new HistorialEmpleado();
        historia.setEpId(getEntidadSeleccion());
        if (null != getEntidadSeleccion().getEsId().getEsId()) switch (getEntidadSeleccion().getEsId().getEsId()) {
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
