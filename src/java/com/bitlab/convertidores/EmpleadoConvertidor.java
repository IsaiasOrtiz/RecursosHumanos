/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.convertidores;

import com.bitlab.controladoresJPA.EmpleadoController;
import com.bitlab.entidades.Empleado;
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
@FacesConverter(forClass = Empleado.class)
public class EmpleadoConvertidor implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            EmpleadoController ep=new EmpleadoController();
            return ep.buscarObject(Integer.parseInt(value));
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoConvertidor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Empleado)value).getEpId().toString();
    }
    
}
