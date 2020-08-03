/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.controladoresJSF;

import com.bitlab.controladoresJPA.AbstractController;
import com.bitlab.controladoresJPA.AreaController;
import com.bitlab.entidades.Area;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author elcon
 */
@ManagedBean
@ViewScoped
public class DepartamentoControlador extends AbstractControlador<Area>{
    private AreaController areaControlador;
    public DepartamentoControlador() {
        super(Area.class);
        areaControlador=new AreaController();
    }

    @Override
    public AreaController getControlador() {
        return areaControlador;
    }

    /**
     * Creates a new instance of DepartamentoControlador
     */
    
    
}
