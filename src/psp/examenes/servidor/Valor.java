/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.examenes.servidor;

import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author alumno
 */
public class Valor {
    
    private String empresa;
    private double cotizacion;

    public Valor(String empresa, double cotizacion) {
        this.empresa = empresa;
        this.cotizacion = cotizacion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public double getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(double cotizacion) {
        this.cotizacion = cotizacion;
    }

    @Override
    public String toString() {
        return "Valor{" + "empresa=" + empresa + ", cotizacion=" + cotizacion + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.empresa);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.cotizacion) ^ (Double.doubleToLongBits(this.cotizacion) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Valor other = (Valor) obj;
        if (Double.doubleToLongBits(this.cotizacion) != Double.doubleToLongBits(other.cotizacion)) {
            return false;
        }
        return Objects.equals(this.empresa, other.empresa);
    }
    
    

    
}
