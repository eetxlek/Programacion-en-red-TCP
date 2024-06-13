package psp.examenes.cliente;

import com.sun.jmx.remote.protocol.rmi.ClientProvider;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import psp.examenes.servidor.Valor;

public class BolsaClient {

    BufferedReader entrada = null;
    PrintWriter salida = null;
    Socket socketCliente = null;
    Scanner stdIn = null;
    String nombre;

    public static void main(String[] args) throws IOException {

        BolsaClient cliente = new BolsaClient();
        cliente.inicializa();
        cliente.protocolo();
    }

    public BolsaClient() {
        this.entrada = null;
        this.salida = null;
        this.socketCliente = null;
        this.stdIn = null;
        nombre = "";
    }

    private void inicializa() {
        // Creamos un socket en el lado cliente, enlazado con un
        // servidor que est� en la misma m�quina que el cliente
        // y que escucha en el puerto 4444
        try {
            socketCliente = new Socket("localhost", 4444);
            // Obtenemos el canal de entrada
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            // Obtenemos el canal de salida
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
        } catch (IOException e) {
            System.err.println("No puede establer canales de E/S para la conexi�n");
            System.exit(-1);
        }
        // Utilizamos un BufferedReader para la lectura del teclado
        stdIn = new Scanner(System.in);

    }

    private void protocolo() throws IOException {
        Scanner lee = new Scanner(System.in);
        String mensaje = "";
        String accion = ""; // completo
        String comando = ""; // el 0

        //CLIENTE SOLO 1 VEZ
        System.out.println("Introduce nombre usuario: ");
        nombre = lee.nextLine();

        //EMITE 1
        salida.println("CLIENTE " + nombre);

        // OTROS COMANDOS 
        while (true) {
            System.out.print("Introduce comando (VALORES / COMPRA / VENDE / CARTERA): ");
            accion = lee.nextLine();
            String[] acciones = accion.split("\\s");
            comando = acciones[0].toUpperCase();

            // tras asegurarse emite todo mensaje
            if (comando.equals("VALORES")) {
                salida.println(comando);// VALORES
                String items = entrada.readLine();
                //ITEMS num
                System.out.println(items);
                String[] it = items.split("\\s");
                int numItem = Integer.parseInt(it[1]); //extrae num
                System.out.println("*VALOR / cotizacion** "); //MUESTRA
                while (numItem > 0) {
                    String valor = entrada.readLine();
                    System.out.println(valor);
                    numItem--;
                }
            } else if (comando.equals("COMPRA")) {
                salida.println(accion); // COMPRA acciona 1
                System.out.println("Valor que quieres comprar: " + acciones[1]);
                System.out.println("Cantidad que quieres comprar: " + acciones[2]);
                //LEE OK O KO
                mensaje = entrada.readLine();
                if (mensaje.equals("OK")) {
                    //MUESTRA   
                    System.out.println("Compra realizada correctamente");
                } else {
                    System.out.println("Error comprando " + acciones[1]);
                }

            } else if (comando.equals("VENDE")) {
                salida.println(accion); // VENDE acciona 1
                System.out.println("Valor que quieres vender: " + acciones[1]);
                System.out.println("Cantidad que quieres vender: " + acciones[2]);
                //LEE OK O KO
                mensaje = entrada.readLine();
                if (mensaje.equals("OK")) {
                    //MUESTRA   
                    System.out.println("Venta realizada correctamente");
                } else {
                    System.out.println("Error vendiendo " + acciones[1]);
                }

            } else if (comando.equals("CARTERA")) {
                System.out.println("** CARTERA de valores**"); // SI MUESTRA
                //CARTERA--- no entra en server
                salida.println(accion);
                //lee lista
                mensaje = entrada.readLine();            
                String[] listaArray = mensaje.split("/"); //SEPARA CADA REGSITRO empre num
                //muestra cada valor en una linea
                for (String x : listaArray) { //muestra cada registro en una fila
                    System.out.println(x);
                }
                // lee total
                mensaje = entrada.readLine();

                //muestra total
                System.out.println("Valor total inversion " + mensaje);

            }
        }
        //SINCRONIZACION DE ESCUCHAS Y EMISIONES AL SERVER WORKER
        //TO-DO: Completa este método con la implementación del protocolo
        //1// cliente emite nombre
        //2// cliente emite valores
        //2// lee 3

    }
}
