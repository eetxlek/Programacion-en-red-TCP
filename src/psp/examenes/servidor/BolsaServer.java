package psp.examenes.servidor;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashAttributeSet;

public class BolsaServer {

    public static final int PORT = 4444;

    public static void main(String[] args) {
        // Establece el puerto en el que escucha peticiones
        ServerSocket socketServidor = null;
        //GENERA EL SERVER CON EL PUERTO.... Generado servicio en el puerto
        try {
            socketServidor = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("No puede escuchar en el puerto: " + PORT);
            System.exit(-1);
        }
        System.out.println("Escuchando: " + socketServidor);

        //Crea lista Valores GENERAL (empresa/cotizacion)
        ArrayList<Valor> lista = new ArrayList();
        lista.add(new Valor("Inditex", 30.2));
        lista.add(new Valor("Acciona", 30.2));
        lista.add(new Valor("BBVA", 30.2));
        lista.add(new Valor("Santander", 30.2));
        lista.add(new Valor("Endesa", 30.2));
        ListaValores valores = new ListaValores(lista);

        //Crea cliente, NO INICIA! dentro del loop con accept()
        Socket socketCliente = null;

        //BUCLE QUE CREA CONECION ENTRE SERVER Y CLIENTE
        while (true) {
            try {
                // Si hay cliente abre socket para el
                socketCliente = socketServidor.accept();
            } catch (IOException ex) {
                Logger.getLogger(BolsaServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Conexion establecida con cliente x
            System.out.println("Connexión acceptada: " + socketCliente);
            //El worker del servidor para cliente se inicia 
            //con socketcliente, y valores y cartera.
            // Crea Hash para la cartera
            HashMap<Valor, Integer> listaMia = new HashMap<>();
            for (Valor v : lista) {
                listaMia.put(v, 0);
            }
            new BolsaWorker(socketCliente, valores, new Cartera(listaMia)).start();
        }

    }
}
