/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controladoresJSF;

import com.bitlab.controladoresJPA.TipoController;
import com.bitlab.entidades.Tipo;
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
public class TipoControlador extends AbstractControlador<Tipo> {

    private TipoController tipoControlador;

    public TipoControlador() {
        super(Tipo.class);
        tipoControlador = new TipoController();
    }

    /**
     * Creates a new instance of TipoControlador
     */
    @Override
    public TipoController getControlador() {
        return tipoControlador;
    }

    @Override
    public void auditoria() {
        getEntidadSeleccion().setAFechaModificacion(new Date());
    }

    @Override
    public boolean validacionEditar() {
        if (getEntidadSeleccion().getTpNombre().isEmpty()) {
            UtilidadesWeb.mensajeAdvertencia("Advertencia!", "El nombre no puede quedar vacio");
            return false;
        } else {
            return true;
        }
    }

}
