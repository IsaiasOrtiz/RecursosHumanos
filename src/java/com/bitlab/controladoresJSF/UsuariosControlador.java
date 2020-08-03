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
public class UsuariosControlador extends AbstractControlador<Usuario> {

    private UsuarioController usuarioControlador;
    private TipoController tipo;
    private EmpleadoController empleado;
    private String clave;

    /**
     * Creates a new instance of UsuariosControlador
     */
    public UsuariosControlador() {
        super(Usuario.class);
        usuarioControlador = new UsuarioController();
        tipo = new TipoController();
        empleado=new EmpleadoController();
    }

    @Override
    public UsuarioController getControlador() {
        return usuarioControlador;
    }

    @Override
    public void encryptar() {
        Encryptacion en = new Encryptacion();
        getEntidadSeleccion().setUsClave(en.getEncryptacion(clave));
    }

    @Override
    public void auditoria() {
        getEntidadSeleccion().setAFechaModificacion(new Date());
    }

    public UsuarioController getUsuarioControlador() {
        return usuarioControlador;
    }

    public void setUsuarioControlador(UsuarioController usuarioControlador) {
        this.usuarioControlador = usuarioControlador;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public TipoController getTipo() {
        return tipo;
    }

    public void setTipo(TipoController tipo) {
        this.tipo = tipo;
    }

    public EmpleadoController getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoController empleado) {
        this.empleado = empleado;
    }
    
}
