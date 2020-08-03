/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author elcon
 */
public class UtilidadesWeb {
    /**
     * Manda un mensaje con FacesMassage recibe la severidad, encabezado y detalle.
     * @param severidad
     * @param encabezado
     * @param detalle 
     */
 public static void lanzarMensaje(FacesMessage.Severity severidad , String encabezado , String detalle)
 {
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidad, encabezado, detalle));
 }
 /**
  * Implementa el metodo lanzar mensaje para lanzar un mensaje de Informacion
  * @param encabezado
  * @param detalle 
  */
 public static void mensajeInfo(String encabezado, String detalle)
 {
     lanzarMensaje(FacesMessage.SEVERITY_INFO, encabezado, detalle);
 }
 /**
  * Implementa el metodo lanzar mensaje para lanzar un mensaje de eror
  * @param encabezado
  * @param detalle 
  */
 public static void mensajeError(String encabezado, String detalle)
 {
     lanzarMensaje(FacesMessage.SEVERITY_ERROR, encabezado, detalle);
 }
 /**
  * Implementa el metodo lanzar mensaje para lanzar un mensaje de advertencia
  * @param encabezado
  * @param detalle 
  */
 public static void mensajeAdvertencia(String encabezado, String detalle)
 {
     lanzarMensaje(FacesMessage.SEVERITY_WARN, encabezado, detalle);
 }
/**
 * Hace una redireccion a un apagina con extencion.bat
 * @param pagina 
 */
 public static void redireccion(String pagina)
 {
     try {
         ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
         context.redirect(context.getRequestContextPath()+"/"+pagina+".bat");
     } catch (IOException ex) {
         Logger.getLogger(UtilidadesWeb.class.getName()).log(Level.SEVERE, null, ex);
     }
 }
}
