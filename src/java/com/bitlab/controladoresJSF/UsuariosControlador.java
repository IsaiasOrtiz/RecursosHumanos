/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controladoresJSF;

import com.bitlab.controladoresJPA.EmpleadoController;
import com.bitlab.controladoresJPA.TipoController;
import com.bitlab.controladoresJPA.UsuarioController;
import com.bitlab.entidades.Empleado;
import com.bitlab.entidades.Tipo;
import com.bitlab.entidades.Usuario;
import com.bitlab.utilidades.Encryptacion;
import java.util.ArrayList;
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
public class UsuariosControlador extends AbstractControlador<Usuario> {

    private String clave;
    private String tipo;
    private String empleado;
    private UsuarioController usuarioControlador;
    private List<SelectItem> listaTipos;
    private List<SelectItem> listaEmpleados;

    /**
     * Creates a new instance of UsuariosControlador
     */
    public UsuariosControlador() {
        super(Usuario.class);
        usuarioControlador = new UsuarioController();

    }

    @Override
    public void join() {
        try {
            EmpleadoController ep = new EmpleadoController();
            TipoController tp = new TipoController();
            tipo = tipo.replaceAll("\\D+", "");
            empleado = empleado.replaceAll("\\D+", "");
            getEntidadSeleccion().setEpId(ep.buscarObject(Integer.parseInt(empleado)));
            getEntidadSeleccion().setTpId(tp.buscarObject(Integer.parseInt(tipo)));
        } catch (Exception ex) {
            Logger.getLogger(UsuariosControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public UsuarioController getControlador() {
        return usuarioControlador;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public List<SelectItem> getListaTipos() {
        listaTipos = new ArrayList<SelectItem>();
        TipoController tp = new TipoController();
        
        List<Tipo> list = tp.encontrarTodos();
        listaTipos.clear();
        for (Tipo e : list) {
            SelectItem item = new SelectItem(e.getTpId() + "-" + e.getTpNombre());
            listaTipos.add(item);
        }
        return listaTipos;
    }

    public List<SelectItem> getListaEmpleados() {
        listaEmpleados = new ArrayList<SelectItem>();
        EmpleadoController tp = new EmpleadoController();
        List<Empleado> list = tp.encontrarTodos();
        listaEmpleados.clear();
        for (Empleado e : list) {
            SelectItem item = new SelectItem(e.getEpId() + "-" + e.getEpNombres());
            listaEmpleados.add(item);
        }
        return listaEmpleados;
    }
    @Override
    public void encryptar()
    {
        Encryptacion en=new Encryptacion();
        getEntidadSeleccion().setUsClave(en.getEncryptacion(clave));
    }
}
