/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.examenes.servidor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author alumno
 */
public class Cartera {

    private HashMap<Valor, Integer> listaMia;

    public Cartera(HashMap<Valor, Integer> listaMia) {
        this.listaMia = listaMia;
    }

    public HashMap<Valor, Integer> getListaMia() {
        return listaMia;
    }

    public void setListaMia(HashMap<Valor, Integer> listaMia) {
        this.listaMia = listaMia;
    }

    public void sumarCantidad(String empresa, Integer cantidad) {

        for (Valor v : listaMia.keySet()) {
            if (v.getEmpresa().equals(empresa)) {
                Integer valorActual = listaMia.get(v);
                Integer newValor = valorActual + cantidad;
                listaMia.put(v, newValor);
            }
        }
    }
    public void restarCantidad(String empresa, Integer cantidad) {
        for (Valor v : listaMia.keySet()) {
            if (v.getEmpresa().equals(empresa)) {
                Integer valorActual = listaMia.get(v);
                Integer newValor = valorActual - cantidad;
                listaMia.put(v, newValor);
            }
        }
    }
    public boolean contiene(Valor v) {
        return listaMia.containsKey(v);
    }

    public Integer cantidad(Valor v) {
        return listaMia.get(v);
    }

    public double calculaInversion() {
        double total = 0;
          for (Map.Entry<Valor, Integer> entry : listaMia.entrySet()) {
            Valor valor = entry.getKey();
            Integer num = entry.getValue();
            if (valor != null && num != null) {
            total += valor.getCotizacion() * num;
        }
        }
        return total;
    }

    public String formatear() {
        StringBuilder registro=new StringBuilder();

        for (Map.Entry<Valor, Integer> entry : listaMia.entrySet()) {
            Valor valor = entry.getKey();
            Integer num = entry.getValue();
            registro.append(valor.getEmpresa()).append(" ").append(num).append("/");
        }
        return registro.toString();
    }

    @Override
    public String toString() {

        return "Cartera{" + "listaMia=" + listaMia + '}';
    }

}
