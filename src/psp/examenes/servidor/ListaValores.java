/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.examenes.servidor;

import java.util.ArrayList;

public class ListaValores {

    private ArrayList<Valor> lista;

    public ListaValores(ArrayList<Valor> lista) {
        this.lista = lista;
    }

    public ArrayList<Valor> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Valor> lista) {
        this.lista = lista;
    }

    public Valor getOne(String empresa) {
       
        for (Valor v : lista) {
            if (v.getEmpresa().equals(empresa)) {
                return v;
            }
        }
        return null;
    }
    public boolean existe(String empresa) {
       
        for (Valor v : lista) {
            if (v.getEmpresa().equals(empresa)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ListaValores{" + "lista=" + lista + '}';
    }

}
