/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.utilidades;

/**
 *
 * @author Douglas Isaias Valle Ortiz
 */
public class PlanillaUtilidad {
    /**
     * Calculamos el ISSS de un salario 
     * que recibimos como parametro
     * y retormanos el ISSS de ese salario.
     * @param salario
     * @return 
     */
    public double isss(double salario) {
        double isss = 0;
        if (salario >= 1000) {
            isss = 30;
        } else if (salario > 0 && salario < 1000) {
            isss = salario * 0.03;
        }
        return isss;
    }
    /**
     * Calculamos el APF de un salario 
     * que recibimos como parametro
     * y retormanos el AFP de ese salario.
     * @param salario
     * @return 
     */
    public double afp(double salario) {
        double afp = 0;
        afp = salario * 0.0725;
        return afp;
    }
    /**
     * Calculamos la renta de un salario 
     * que recibimos como parametro
     * y retormanos la renta de ese salario.
     * @param salario
     * @return 
     */
    public double renta(double salario) {
        double renta = 0;
        double salarioMenosAfpIsss = salario - (afp(salario) + isss(salario));
        if (salarioMenosAfpIsss < 470) {
            renta = 0;
        } else if (salarioMenosAfpIsss > 470 && salarioMenosAfpIsss < 895.24) {
            renta = ((salarioMenosAfpIsss-472)*0.10)+17.67;
        } else if (salarioMenosAfpIsss > 895.25 && salarioMenosAfpIsss < 2038.1) {
            renta = ((salarioMenosAfpIsss-895.24)*0.20)+60;
        } else if (salarioMenosAfpIsss > 2038.11) {
            renta = ((salarioMenosAfpIsss-2038.10)*0.30)+288.57;
        }
        return renta;
    }
    /**
     * Calculamos el sueldo neto de un salario 
     * que recibimos como parametro.
     * @param salario
     * @return 
     */
    public double sueldoNeto(double salario)
    {
        double salarioN=salario - (isss(salario)+afp(salario)+renta(salario));
        return salarioN;
    }
    /**
     * Retorna una cadena con los descuentos 
     * que se le aplican a nuestro salario.
     * @param salario
     * @return 
     */
    public String resumenSalario(double salario)
    {
    String str="AFP: "+afp(salario)
            +"\nISSS: "+isss(salario)
            +"\nRenta: "+renta(salario)
            +"\nSueldo neto: " + sueldoNeto(salario);
    return str;
    }
}
