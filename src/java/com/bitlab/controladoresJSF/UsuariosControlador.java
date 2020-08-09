/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controladoresJSF;

import com.bitlab.controladoresJPA.EmpleadoController;
import com.bitlab.controladoresJPA.TipoController;
import com.bitlab.controladoresJPA.UsuarioController;
import com.bitlab.entidades.Usuario;
import com.bitlab.utilidades.Encryptacion;
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
        empleado = new EmpleadoController();
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

    @Override
    public boolean validacionEditar() {
        boolean validarU = true;
        if (getEntidadSeleccion().getUsUsuario().isEmpty()) {
            UtilidadesWeb.mensajeAdvertencia("Advertencia!", "El usuario no puede quedar vacio");
            validarU = false;
        }
        if (clave.isEmpty()) {
            UtilidadesWeb.mensajeAdvertencia("Advertencia!", "La clave no puede quedar vacia");
            validarU = false;
        }
        return validarU;
    }
    @Override 
    public boolean validacionGuardar()    
    {
        return validacionEditar();
    }
}
