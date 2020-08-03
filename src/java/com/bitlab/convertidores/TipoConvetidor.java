/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.convertidores;

import com.bitlab.controladoresJPA.TipoController;
import com.bitlab.entidades.Tipo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author elcon
 */
@FacesConverter(forClass = Tipo.class)
public class TipoConvetidor implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            TipoController tp=new TipoController();
            return  tp.buscarObject(Integer.parseInt(value));
        } catch (Exception ex) {
            Logger.getLogger(TipoConvetidor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Tipo)value).getTpId().toString();
    }
    
}
