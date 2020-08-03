/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.convertidores;

import com.bitlab.controladoresJPA.AreaController;
import com.bitlab.entidades.Area;
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
@FacesConverter(forClass = Area.class)
public class DepartamentoConvertidor implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            AreaController ar = new AreaController();
            return ar.buscarObject(Integer.parseInt(value));
        } catch (Exception ex) {
            Logger.getLogger(DepartamentoConvertidor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Area)value).getDpId().toString();
    }

}
