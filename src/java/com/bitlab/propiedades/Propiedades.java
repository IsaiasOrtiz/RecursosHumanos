/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.propiedades;

import java.io.InputStream;

/**
 *
 * @author Douglas Isaias Valle Ortiz
 */
public class Propiedades {
     public static InputStream flujoDeDatos(String nombre) {
    
        return Propiedades.class.getResourceAsStream(nombre);
    }
}
