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
import com.echo.utilidades.Aleatorio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author elcon
 */
@ManagedBean
@SessionScoped
public class LoginControlador implements Serializable {

    private String usuario;
    private String clave;
    private int tipo;
    private UsuarioController us;
    private int code = -1;
    private int codeUsuario;
    private boolean sessionActiva;

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
        sessionActiva = false;
        try {
            Usuario user = us.getUsuario(usuario);
            System.out.println(user.getUsClave());
            if (user != null) {
                Encryptacion enc = new Encryptacion();
                if ((enc.getDesencryptacion(user.getUsClave())).equals(clave)) {

                    Aleatorio al = new Aleatorio();
                    code = al.numAleatorio();
                    Mensajes msj = new Mensajes();
                    msj.enviarMensaje("Codigo de verificacion", code + "", usuario);
                    tipo = user.getTpId().getTpId();
                    sessionActiva = false;
                    intentos = 0;
                    UtilidadesWeb.redireccion("verificacion");
                    clave = null;
                } else {
                    user = null;
                }
            } else {
                UtilidadesWeb.mensajeError("Error", "Usuario o clave invalido.");
            }
        } catch (Exception e) {
            UtilidadesWeb.mensajeError("Error", "Usuario o clave invalido..");
        }

    }
    /**
     * Valida el codigo de verificacion para poder entrar el sistema
     */
    byte intentos = 0;

    public void validar() {

        if (code != (-1)) {
            if (codeUsuario == code) {
                Usuario user = us.getUsuario(usuario);
                setTipo(user.getTpId().getTpId());
                if (tipo == 1) {
                    code = -1;
                    sessionActiva = true;
                    UtilidadesWeb.redireccion("iniciorh");
                } else if (tipo == 2) {
                    sessionActiva = true;
                    UtilidadesWeb.redireccion("inicioadm");
                    code = -1;
                }
            } else {
                sessionActiva = false;
                intentos++;
                UtilidadesWeb.mensajeError("Codigo invalido", "Intentos restantes " + (3 - intentos));
                if (intentos == 3) {
                    cerrarObjetos();
                }

            }
        } else {
            cerrarSesion();

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
        if (sessionActiva) {
            if (!(tipo == 1)) {
                UtilidadesWeb.redireccion("404");
            }
        } else {
            UtilidadesWeb.redireccion("404");
        }

    }

    public void validarSessionAdmin() {
        if (sessionActiva) {
            if (!(tipo == 2)) {
                UtilidadesWeb.redireccion("404");
            }
        } else {
            UtilidadesWeb.redireccion("404");
        }
    }

    /**
     * Metodo especial para la validacion del doble factor de autenticacion el
     * cual solo deja estar en esa pagina a personas con el tipo 1 o 2 rrhh o
     * admin
     */
    public void dobleFactor() {
        if (!(tipo == 1 || tipo == 2)) {
            UtilidadesWeb.redireccion("index");
        }
        activeSesion();
    }

    public void cerrarSesion() {
        cerrarObjetos();
        UtilidadesWeb.redireccion("index");
    }

    public void cerrarObjetos() {
        usuario = null;
        tipo = 0;
        usuario = null;
        clave = null;
        code = -1;
    }

    public void activeSesion() {
        if (sessionActiva) {
            if(tipo==1)
            UtilidadesWeb.redireccion("iniciorh");
            if(tipo==1)
            UtilidadesWeb.redireccion("inicioadm");
        }
    }

}
