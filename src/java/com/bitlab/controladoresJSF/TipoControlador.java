/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controladoresJSF;

import com.bitlab.controladoresJPA.TipoController;
import com.bitlab.entidades.Tipo;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author elcon
 */
@ManagedBean
@ViewScoped
public class TipoControlador extends AbstractControlador<Tipo>{
    private TipoController tipoControlador;
    public TipoControlador() {
        super(Tipo.class);
        tipoControlador=new TipoController();
    }

    /**
     * Creates a new instance of TipoControlador
     */
  

    @Override
    public TipoController getControlador() {
       return tipoControlador;
    }
    
}
