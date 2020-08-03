/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controladoresJSF;

import com.bitlab.controladoresJPA.UsuarioController;
import com.bitlab.entidades.Usuario;
import com.bitlab.utilidades.Encryptacion;
import com.bitlab.utilidades.Mensajes;
import com.bitlab.utilidades.UtilidadesWeb;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author elcon
 */
@ManagedBean
@SessionScoped
public class LoginControlador {

    private String usuario;
    private String clave;
    private int tipo;
    private UsuarioController us;
    private int code = -1;
    private int codeUsuario;

    /**
     * Creates a new instance of LoginControlador
     */
    public LoginControlador() {

    }

    @PostConstruct
    public void cargarPropiedades() {
        us = new UsuarioController();
    }

    public void login() {
        try {
            Usuario user = us.getUsuario(usuario);
            if (user != null) {
                Encryptacion enc = new Encryptacion();
                if ((enc.getDesencryptacion(user.getUsClave())).equals(clave)) {
                    code = ((int) (Math.random() * 1000000 + 100000));
                    Mensajes msj = new Mensajes();
                    msj.enviarMensaje("Codigo de verificacion", code + "", usuario);
                    clave = "";
                }
            } else {
                UtilidadesWeb.mensajeError("Error", "Usuario o clave invalido.");
            }
        } catch (Exception e) {
            UtilidadesWeb.mensajeError("Error", "Usuario o clave invalido.");
        }

    }

    public void validar() {

        if (code != (-1)) {
            if (codeUsuario == code) {
                Usuario user = us.getUsuario(usuario);
                setTipo(user.getTpId().getTpId());
                if (tipo == 1) {
                    UtilidadesWeb.redireccion("empleados");
                } else if (tipo == 2) {
                }
            } else {
                code = -1;
            }
        } else {
            UtilidadesWeb.redireccion("index");
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getCodeUsuario() {
        return codeUsuario;
    }

    public void setCodeUsuario(int codeUsuario) {
        this.codeUsuario = codeUsuario;
    }

    public void validarSessionRh() {
        try {
            if (tipo == 0) {
                cerrarSesion();
                UtilidadesWeb.redireccion("index");

            }
            if (tipo == 1) {

            } else {
                cerrarSesion();
                UtilidadesWeb.redireccion("index");

            }
        } catch (Exception e) {
            cerrarSesion();
            UtilidadesWeb.redireccion("index");
        }

    }

    public void validarSessionAdmin() {
        try {
            if (tipo == 0) {
                cerrarSesion();
                UtilidadesWeb.redireccion("index");  
            }
            if (tipo == 1) {
                cerrarSesion();
                UtilidadesWeb.redireccion("index");  
            }
            if (tipo == 2) {
            } else {
                cerrarSesion();
                UtilidadesWeb.redireccion("index");
            }
        } catch (Exception e) {
            cerrarSesion();
            UtilidadesWeb.redireccion("index");
        }

    }

    public void cerrarSesion() {
        tipo = 0;
        usuario = "";
        clave = "";
        code=-1;
        UtilidadesWeb.redireccion("index");
    }

}
