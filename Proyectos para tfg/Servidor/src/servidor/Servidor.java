/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import bongoplayerpojo.ExcepcionBP;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    
        ServerSocket socketServidor = null;
        Socket clienteConectado = null;
        
        try { 
            int puertoServidor = 30500;
            socketServidor = new ServerSocket(puertoServidor);            

            while(true){
                clienteConectado = socketServidor.accept();
                hiloPeticion hp = new hiloPeticion(socketServidor, clienteConectado);
                hp.start();
            }
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
        }
    }
}
