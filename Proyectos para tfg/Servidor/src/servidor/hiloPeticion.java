/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import bongoplayercad.BongoPlayerCAD;
import bongoplayerpojo.Cancion;
import bongoplayerpojo.ExcepcionBP;
import bongoplayerpojo.Lista;
import bongoplayerpojo.Lista_Cancion;
import bongoplayerpojo.Usuario;
import bongoplayerpojocomunicaciones.ExcepcionBPComunicaciones;
import bongoplayerpojocomunicaciones.Peticion;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class hiloPeticion extends Thread{
    
    BongoPlayerCAD cad;
    ServerSocket socketServidor;
    Socket clienteConectado;

    public hiloPeticion(ServerSocket socketServidor, Socket clienteConectado) {
        this.socketServidor = socketServidor;
        this.clienteConectado = clienteConectado;
        try {
            cad = new BongoPlayerCAD();
        } catch (ExcepcionBP ex) {
            System.out.println("| -> " + ex.getMensajeErrorUser());
            System.out.println(ex.getMessage()+ " " +ex.getMensajeErrorAdmin());
        }    
    }
    
    @Override
    public void run(){
        
        Object respuesta = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        
        try { 
            ois = new ObjectInputStream(clienteConectado.getInputStream());
            Peticion peticion = (Peticion) ois.readObject();
            System.out.println(peticion);

            Integer registrosAfectados;
            int idOperacion = peticion.getIdOperacion();

            switch (idOperacion){
                case Peticion.INSERTAR_USUARIO:
                    registrosAfectados = insertarUsuario(peticion);
                    respuesta = registrosAfectados;
                    break;
                case Peticion.ELIMINAR_USUARIO:
                    registrosAfectados = eliminarUsuario(peticion);
                    respuesta = registrosAfectados;
                    break;
                case Peticion.MODIFICAR_USUARIO:
                    registrosAfectados = modificarUsuario(peticion);
                    respuesta = registrosAfectados;
                    break;
                case Peticion.LEER_USUARIO:
                    try {
                            Usuario usuario;
                            usuario = leerUsuario(peticion);
                            respuesta = usuario;
                            System.out.println(respuesta.toString()); 
                    } catch (ExcepcionBP ex) {
                        respuesta = ex;
                    }
                    break;
                case Peticion.LEER_USUARIOS:      
                    ArrayList<Usuario> usuarios = new ArrayList<>();
                    usuarios.addAll(leerUsuarios());
                    respuesta = usuarios;
                    break;
                    
                //-------------------------------------------------------
                    
                case Peticion.INSERTAR_CANCION:
                    registrosAfectados = insertarCancion(peticion);
                    respuesta = registrosAfectados;
                    break;
                case Peticion.ELIMINAR_CANCION:
                    //registrosAfectados = eliminarUsuario(peticion);
                    respuesta = 0;
                    break;
                case Peticion.MODIFICAR_CANCION:
                    //registrosAfectados = modificarUsuario(peticion);
                    respuesta = 0;
                    break;
                case Peticion.LEER_CANCION:
                    //Cancion canciones = leerUsuario(peticion);
                    //respuesta = "";
                    break;
                case Peticion.LEER_CANCIONES: 
                    ArrayList<Cancion> canciones = leerCanciones();
                    respuesta = canciones;
                    break;
                
                //-------------------------------------------------------
                    
                case Peticion.INSERTAR_LISTA:
                    registrosAfectados = insertarLista(peticion);
                    respuesta = registrosAfectados;
                    break;
                case Peticion.ELIMINAR_LISTA:
                    //registrosAfectados = eliminarUsuario(peticion);
                    respuesta = 0;
                    break;
                case Peticion.MODIFICAR_LISTA:
                    //registrosAfectados = modificarUsuario(peticion);
                    respuesta = 0;
                    break;
                case Peticion.LEER_LISTA:
                    //Cancion canciones = leerUsuario(peticion);
                    //respuesta = "";
                    break;
                case Peticion.LEER_LISTAS: 
                    ArrayList<Lista> listas = leerListas();
                    respuesta = listas;
                    break;
                      
                //-------------------------------------------------------
                    
                case Peticion.INSERTAR_LISTA_CANCION:
                    registrosAfectados = insertarLista_Cancion(peticion);
                    respuesta = registrosAfectados;
                    break;
                case Peticion.LEER_LISTAS_CANCIONES:
                    ArrayList<Lista_Cancion> listas_canciones = leerListas_Canciones();
                    respuesta = listas_canciones;
                    break;
                    
                default:
                    System.out.println("Log class not found excepcion"+"Operacion no soportada en el protocolo");
                    ExcepcionBPComunicaciones e = new ExcepcionBPComunicaciones();
                    e.setMemensajeErrorAdmin("Operacion no soportada en el protocolo");
                    e.setMemensajeErrorUser("Error general del sistema");
                    respuesta = e;
            }       

        } catch (ClassCastException ex) {
            System.out.println("Log ClassCastException" + ex.getMessage());
            ExcepcionBPComunicaciones e = new ExcepcionBPComunicaciones();
            e.setMemensajeErrorAdmin(ex.getMessage());
            e.setMemensajeErrorUser("Error General del Sistema");
            respuesta = e;
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Log ClassNotFoundException: " +ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Log: " + ex.getMessage());
        }

        try {
            oos = new ObjectOutputStream(clienteConectado.getOutputStream());
            oos.writeObject(respuesta);
            System.out.println(respuesta);
            
            oos.close();
            ois.close();  
        } catch (IOException ex) {
            System.out.println("Log: " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("Log: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Log: " + ex.getMessage());
        } 
    }   
    
    
    private Integer insertarUsuario(Peticion peticion) throws ExcepcionBP
    {
        int usuario = cad.insertarUsuario((Usuario) peticion.getObjeto());
        return usuario;
    }
    private Integer eliminarUsuario(Peticion peticion) throws ExcepcionBP
    {
        int usuario = cad.eliminarUsuario(peticion.getPk());
        return usuario;
    }
    private Integer modificarUsuario(Peticion peticion) throws ExcepcionBP
    {
        int usuario = cad.actualizarUsuario(peticion.getPk(), (Usuario) peticion.getObjeto());
        return null;
    }
    private Usuario leerUsuario(Peticion peticion) throws ExcepcionBP
    {
        Usuario usuario = cad.leerUsuario(peticion.getPk());
        return usuario;
    }
    private ArrayList<Usuario> leerUsuarios() throws ExcepcionBP
    {
        ArrayList<Usuario> usuarios = cad.leerUsuarios();
        return usuarios;
    }
    
    //--------------------------------------------------------------------------
    
    private Integer insertarCancion(Peticion peticion) throws ExcepcionBP
    {
        int cancion = cad.insertarCancion((Cancion) peticion.getObjeto());
        return cancion;
    }
    
    private ArrayList<Cancion> leerCancion(Peticion peticion) throws ExcepcionBP
    {
        ArrayList<Cancion> cancion = cad.leerCancion(peticion.getPk());
        return cancion;
    }
    
    private ArrayList<Cancion> leerCanciones() throws ExcepcionBP
    {
        ArrayList<Cancion> canciones = cad.leerCanciones();
        return canciones;
    }
    
    //--------------------------------------------------------------------------
    
    private Integer insertarLista(Peticion peticion) throws ExcepcionBP
    {
        int lista = cad.insertarLista((Lista) peticion.getObjeto());
        return lista;
    }
    private Integer eliminarLista(Peticion peticion) throws ExcepcionBP
    {
        int lista = cad.eliminarLista(peticion.getPk());
        return lista;
    }
    private Integer modificarLista(Peticion peticion) throws ExcepcionBP
    {
        int lista = cad.actualizarLista(peticion.getPk(), (Lista) peticion.getObjeto());
        return null;
    }
    private Lista leerLista(Peticion peticion) throws ExcepcionBP
    {
        Lista lista = cad.leerLista(peticion.getPk());
        return lista;
    }
    private ArrayList<Lista> leerListas() throws ExcepcionBP
    {
        ArrayList<Lista> listas = cad.leerListas();
        return listas;
    }
    
    //--------------------------------------------------------------------------
    
    private Integer insertarLista_Cancion(Peticion peticion) throws ExcepcionBP
    {
        int lista = cad.insertarLista_Cancion((Lista_Cancion) peticion.getObjeto());
        return lista;
    }
    
    private ArrayList<Lista_Cancion> leerListas_Canciones() throws ExcepcionBP
    {
        ArrayList<Lista_Cancion> listas_canciones = cad.leerListas_Canciones();
        return listas_canciones;
    }
}
