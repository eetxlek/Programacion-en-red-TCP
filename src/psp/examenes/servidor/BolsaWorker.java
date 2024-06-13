/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.examenes.servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class BolsaWorker extends Thread {

    private Socket socketCliente;
    private BufferedReader entrada;
    private PrintWriter salida;
    private ListaValores listaValores;
    private String nombre;
    private Cartera cartera;

    public BolsaWorker(Socket socketCliente, ListaValores ListaValores, Cartera x) {
        this.socketCliente = socketCliente;
        this.listaValores = ListaValores;
        this.cartera = x;
    }

    public Socket getSocketCliente() {
        return socketCliente;
    }

    public void setSocketCliente(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    public BufferedReader getEntrada() {
        return entrada;
    }

    public void setEntrada(BufferedReader entrada) {
        this.entrada = entrada;
    }

    public PrintWriter getSalida() {
        return salida;
    }

    public void setSalida(PrintWriter salida) {
        this.salida = salida;
    }

    public ListaValores getListaValores() {
        return listaValores;
    }

    public void setListaValores(ListaValores listaValores) {
        this.listaValores = listaValores;
    }

    @Override
    public void run() {
        //crea entradas y salidas del socket
        try {
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            // Obtenemos el canal de salida
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);

            while (true) {
                //LEE ACCION CON PARAMETROS
                String accion = entrada.readLine();
                String[] mensaje = accion.split("\\s");
                String comando = mensaje[0]; //COMANDO
                String empresa = ""; //empresa
                Integer cantidad = 0;

                switch (comando) {
                    case "CLIENTE":  // LEE antes CLIENTE
                        nombre = mensaje[1];
                        System.out.println("--> " + comando + " " + nombre);
                        break;

                    case "VALORES": //LEE antes VALORES 
                        //muestra en server comando y parm

                        System.out.println(nombre + " --> " + comando);
                        //  ITEMS del servidor
                        salida.println("ITEMS " + listaValores.getLista().size());

                        for (Valor x : listaValores.getLista()) {
                            salida.println(x);
                        }
                        break;

                    case "COMPRA":
                        //muestra COMPRA empresa num en server
                        System.out.println(nombre + " --> " + accion);

                        empresa = mensaje[1]; //empresa
                        cantidad = Integer.valueOf(mensaje[2]); //cantidad

                        Valor vC = listaValores.getOne(empresa); //mira si está en lista y añade
                        if (vC != null) {
                            salida.println("OK");
                            //UPDATE
                          
                            cartera.sumarCantidad(empresa, cantidad);
                        } else {// SI NO ESTÁ
                            salida.println("KO");
                        }
                        break;

                    case "VENDE":
                        System.out.println(nombre + " --> " + accion);
                        empresa = mensaje[1]; //empresa
                        cantidad = Integer.valueOf(mensaje[2]); //cantidad en Integer

                        Valor vV = listaValores.getOne(empresa); //mira si está en lista y añade
                        boolean existeEnCartera= cartera.contiene(vV);
                        Integer cantidadCartera= cartera.cantidad(vV);
                        if (vV != null && existeEnCartera && cantidadCartera>=cantidad) {
                            salida.println("OK");
                            //UPDATE
                            cartera.restarCantidad(empresa, cantidad);
                        } else {// SI NO ESTÁ
                            salida.println("KO");
                        }
                        break;
                     
                    case "CARTERA":
                        System.out.println(nombre + " --> " + accion); //*****
                        salida.println(cartera.formatear());
                        salida.println(cartera.calculaInversion());
                        break;                        
                }
            }
        } catch (IOException e) {
            System.err.println("No puede establer canales de E/S para la conexi�n");
            System.exit(-1);
        }
    }
}
