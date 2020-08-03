/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controladoresJSF;

import com.bitlab.controladoresJPA.PagosController;
import com.bitlab.entidades.Pagos;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author elcon
 */
@ManagedBean
@ViewScoped
public class PagosControlador extends AbstractControlador<Pagos>{
    
    private PagosController pagosController;
    public PagosControlador() {
        super(Pagos.class);
        pagosController=new PagosController();
    }

    @Override
    public PagosController getControlador() {
        return pagosController;
    }

    /**
     * Creates a new instance of PagosControlador
     */
    
    
}
