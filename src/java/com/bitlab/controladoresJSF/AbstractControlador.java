/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controladoresJSF;

import com.bitlab.controladoresJPA.AbstractController;
import com.bitlab.utilidades.UtilidadesWeb;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *
 * @author elcon
 */
public abstract class AbstractControlador<T> implements Serializable {
    private final String FALLO="Algo fallo ";
    private final String ERROR = "";
    private final String EXITO ="";
    private T entidadSeleccion;
    private List<T> entidadLista;
    private Class<T> claseEntidad;

    public AbstractControlador(Class<T> claseEntidad) {
        this.claseEntidad = claseEntidad;
    }

    @PostConstruct
    public void cargarInformacion() {
        try {
            entidadLista = getControlador().encontrarTodos();
            nuevaEntidad();
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(AbstractControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void destruir() {
        entidadLista = null;
        entidadSeleccion = null;
    }

    public void nuevaEntidad() throws NoSuchMethodException {
        try {
            entidadSeleccion = claseEntidad.getConstructor().newInstance();
            UtilidadesWeb.mensajeInfo("Info", "Nuevo: " + claseEntidad.getSimpleName());
        } catch (InstantiationException ex) {
            Logger.getLogger(AbstractControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AbstractControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AbstractControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(AbstractControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarEntidad() {
        if (validacionEditar()) {
            try {
                auditoria();
                getControlador().editar(entidadSeleccion);
                historial();
                cargarInformacion();
                UtilidadesWeb.mensajeInfo(EXITO, "El " + claseEntidad.getSimpleName() + " ha sido guardado");
            } catch (Exception e) {
                UtilidadesWeb.mensajeError(ERROR, FALLO + e.getMessage());
            }
        }
    }

    public void guardarNuevaEntidad() {
        if (validacionGuardar()) {
            try {
                encryptar();
                getControlador().crear(entidadSeleccion);
                historial();
                cargarInformacion();
                UtilidadesWeb.mensajeInfo(EXITO, "El " + claseEntidad.getSimpleName() + " ha sido guardado");
            } catch (Exception e) {
                UtilidadesWeb.mensajeError(ERROR, FALLO + e.getMessage());
            }
        }
    }

    public void eliminarEntidad() {
        try {
            getControlador().destruir(entidadSeleccion);
            cargarInformacion();
            UtilidadesWeb.mensajeInfo(EXITO, "se elimino " + claseEntidad.getSimpleName());
        } catch (Exception e) {
            UtilidadesWeb.mensajeError(ERROR, FALLO + e.getMessage());
        }
    }

    public T getEntidadSeleccion() {
        return entidadSeleccion;
    }

    public void setEntidadSeleccion(T entidadSeleccion) {
        this.entidadSeleccion = entidadSeleccion;
    }

    public List<T> getEntidadLista() {
        return entidadLista;
    }

    public void setEntidadLista(List<T> entidadLista) {
        this.entidadLista = entidadLista;
    }

    public abstract void auditoria();

    public abstract AbstractController<T> getControlador();

    public abstract boolean validacionEditar();

    public void historial() {
    }

    public void encryptar() {
    }

    public boolean validacionGuardar() {
        return true;
    }

}
