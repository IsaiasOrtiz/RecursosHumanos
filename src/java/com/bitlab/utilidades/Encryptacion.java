/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;

import com.bitlab.propiedades.Propiedades;
import java.io.IOException;
import java.util.Properties;
import org.jasypt.util.text.BasicTextEncryptor;


/**
 * Creado para encryptacion de datos.
 * @author Douglas Isaias Valle Ortiz
 * @version 1.0
 */
public class Encryptacion {
    
    private static final String PAS1="GG_ECHO_BIT_BASE23";
    private String conf="config.properties";
    BasicTextEncryptor bs=new BasicTextEncryptor();
    /**
     * Costructor vacio que inicializa la clave de encryptacion.
     */
    public Encryptacion() {
        try {
            Properties pro=new Properties();
            //cargo la clave de las propiedades
            pro.load(Propiedades.flujoDeDatos(conf));
            bs.setPassword(PAS1);
            String valor2=bs.decrypt(pro.getProperty("enp"));
            //formo una nueva clave uniendo la de la clase con la del config
            valor2=PAS1+valor2;
            bs=null;
            bs=new BasicTextEncryptor();
            if(bs!=null)
                bs.setPassword(valor2);
        } catch (IOException ex) {
           
        }
    }
    /**
     * Constructor que cambia la clave de encryptacion.
     * @param CLAVE 
     * En caso de recibir CLAVE vacia pone clave por defecto.
     */
    public Encryptacion(final String CLAVE) {
        if(bs!=null){
           if(CLAVE.isEmpty())
               bs.setPassword(PAS1);
           else
            bs.setPassword(CLAVE);
        }else
        bs=new BasicTextEncryptor();
    }
    /**
     * retorna campo encryptado.
     * @param campo
     * @return 
     */
    public String getEncryptacion(String campo)
    {
        return bs.encrypt(campo);
    }
    /**
     * Retorna campo desencryptado.
     * @param campo
     * @return 
     */
    public String getDesencryptacion(String campo)
    {
        return bs.decrypt(campo);
    } 
   
}
