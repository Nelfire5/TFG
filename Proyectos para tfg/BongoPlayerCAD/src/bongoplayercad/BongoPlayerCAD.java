/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bongoplayercad;

import bongoplayerpojo.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Esta clase implemeta el componente de acceso a datos para la base de datos BongoPlayer
 * @author Nelson Macedo
 * @version 1.0
 * @since 1.0
 */
public class BongoPlayerCAD {

    Connection conexion;
    
    public BongoPlayerCAD() throws ExcepcionBP {
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
        } catch (ClassNotFoundException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
            throw e;
        }
    }

    // Metodo que gestiona la conexion a la BD
    private void conectar() throws ExcepcionBP{
        
        try {
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "PLAYER", "kk");
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(null);
            e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
            throw e;
        }
    }
    
    //---------------------------------------------------------
    //---------------------------------------------------------
    
    /**
    * Inserta un registro en la tabla USUARIO
    * @param usuario Datos del usuario a insertar
    * @return Cantidad de registros insertados
    * @throws ExcepcionBP si se produce cualquier error en la inserccion del usuario en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public int insertarUsuario(Usuario usuario) throws ExcepcionBP{
        
        conectar();
        int registrosAfectados = 0;
        String dml = "INSERT INTO USUARIO (USUARIO_ID,NOMBRE_USUARIO,APELLIDO1,APELLIDO2,CORREO,CONTRASENA,ICONO_USUARIO,ALIAS) " +
                     "VALUES (S_LISTA.nextval,?,?,?,?,?,?,?)";
        
        try {
            
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            sentenciaPreparada.setString(1, usuario.getNombreUsuario());
            sentenciaPreparada.setString(2, usuario.getApellido1());
            sentenciaPreparada.setString(3, usuario.getApellido2());
            sentenciaPreparada.setString(4, usuario.getCorreo());
            sentenciaPreparada.setString(5, usuario.getContrasena());
            sentenciaPreparada.setString(6, usuario.getIconoUsuario());
            sentenciaPreparada.setString(7, usuario.getAlias());
            
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch(ex.getErrorCode()){
                
                case 1:     e.setMensajeErrorUser("El campo Correo o Alias ya esta en uso, no se puede repetir. "
                        + "En caso de que el error persista consulte con el admin");
                            break;
                            
                case 2290:  e.setMensajeErrorUser("El formato de correo o de Alias no es el correcto");
                            break;
                            
                case 1400:  e.setMensajeErrorUser("Debe introducir un dato en los campos obligatorios");
                            break;
                            
                default :   e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
                            break;    
            }
            
            throw e;
        }
        return registrosAfectados;
    }
    /**
    * Elimina un registro en la tabla USUARIO
    * @param usuarioId ID del usuario a eliminar
    * @return Cantidad de registros insertados
    * @throws ExcepcionBP si se produce cualquier error en la inserccion del usuario en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public int eliminarUsuario(Integer usuarioId) throws ExcepcionBP{
        
        conectar();
        int registrosAfectados = 0;      
        String dml = "DELETE FROM USUARIO WHERE USUARIO_ID="+usuarioId;

        try {
            
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
            throw e;
        }
        return registrosAfectados;
    }
    /**
    * Actualiza un registro en la tabla USUARIO
    * @param usuario Datos del usuario a modificar
    * @param usuarioId ID del usuario que se va a modificar
    * @return Cantidad de registros insertados
    * @throws ExcepcionBP si se produce cualquier error en la inserccion del usuario en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public int actualizarUsuario(Integer usuarioId, Usuario usuario) throws ExcepcionBP{
        
        conectar();
        int registrosAfectados = 0;      
        String dml = "UPDATE USUARIO set NOMBRE_USUARIO=? ,APELLIDO1=? "
                + ",APELLIDO2=? ,CORREO=? ,CONTRASENA=?, ICONO_USUARIO=? ,ALIAS=? "
                + "WHERE USUARIO_ID="+usuarioId;
        try {
            
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            sentenciaPreparada.setString(1, usuario.getNombreUsuario());
            sentenciaPreparada.setString(2, usuario.getApellido1());
            sentenciaPreparada.setString(3, usuario.getApellido2());
            sentenciaPreparada.setString(4, usuario.getCorreo());
            sentenciaPreparada.setString(5, usuario.getContrasena());
            sentenciaPreparada.setString(6, usuario.getIconoUsuario());
            sentenciaPreparada.setString(7, usuario.getAlias());
            
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch(ex.getErrorCode()){
                case 1:     e.setMensajeErrorUser("El campo Correo o Alias ya esta en uso, no se puede repetir. "
                        + "En caso de que el error persista consulte con el admin");
                            break;
                            
                case 2290:  e.setMensajeErrorUser("El formato de correo o de Alias no es el correcto");
                            break;
                            
                case 1400:  e.setMensajeErrorUser("Debe introducir un dato en los campos obligatorios");
                            break;
                            
                default :   e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
                            break;    
            }
            throw e;
        }
        return registrosAfectados;
    }
    /**
    * Lee un registro en la tabla USUARIO
    * @param usuarioId ID del usuario que se va a leer
    * @return Un objeto de tipo Usuario
    * @throws ExcepcionBP si se produce cualquier error en la inserccion del usuario en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public Usuario leerUsuario(Integer usuarioId) throws ExcepcionBP{
        conectar();
        Usuario u = new Usuario();
        String dql = "select * from USUARIO "
                    + "where USUARIO_ID = "+usuarioId;

        try {
            
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            
            if(resultado.next())
            {
                u.setUsuarioId(resultado.getInt("usuario_id"));
                u.setNombreUsuario(resultado.getString("nombre_usuario"));
                u.setApellido1(resultado.getString("apellido1"));
                u.setApellido2(resultado.getString("apellido2"));
                u.setCorreo(resultado.getString("correo"));
                u.setContrasena(resultado.getString("contrasena"));
                u.setIconoUsuario(resultado.getString("icono_usuario"));
                u.setAlias(resultado.getString("alias"));   
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
            throw e;
        }
        return u;
    }
    /**
    * Lee todos los registros en la tabla USUARIO
    * @return Un objeto de tipo Usuario
    * @throws ExcepcionBP si se produce cualquier error en la inserccion del usuario en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public ArrayList<Usuario> leerUsuarios( ) throws ExcepcionBP{
        
        conectar();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String dql = "SELECT * from USUARIO ";

        try {
            
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            
            while(resultado.next())
            {
                Usuario u = new Usuario();
                u.setUsuarioId(resultado.getInt("usuario_id"));
                u.setNombreUsuario(resultado.getString("nombre_usuario"));
                u.setApellido1(resultado.getString("apellido1"));
                u.setApellido2(resultado.getString("apellido2"));
                u.setCorreo(resultado.getString("correo"));
                u.setContrasena(resultado.getString("contrasena"));
                u.setIconoUsuario(resultado.getString("icono_usuario"));
                u.setAlias(resultado.getString("alias")); 
                usuarios.add(u);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
            throw e;
        }
        return usuarios;
    }
    
    //------------------------------------------------------------------
    //------------------------------------------------------------------
    //------------------------------------------------------------------
    
    /**
    * Inserta un registro en la tabla Lista
    * @param lista Datos de la lista a insertar
    * @return Cantidad de registros insertados
    * @throws ExcepcionBP si se produce cualquier error en la inserccion de la lista en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public int insertarLista(Lista lista) throws ExcepcionBP{
        
        conectar();
        int registrosAfectados = 0;
        String dml = "INSERT INTO LISTA (LISTA_ID,NOMBRE_LISTA,ICONO_LISTA,FECHA_CREACION,USUARIO_ID)" +
                    " VALUES (S_LISTA.nextval,?,?,default,?)";        
        try {
                       
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            sentenciaPreparada.setString(1, lista.getNombreLista());
            sentenciaPreparada.setString(2, lista.getIconoLista());
            sentenciaPreparada.setInt(3, lista.getUsuario().getUsuarioId());
            
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
                
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()){
                case 1:     e.setMensajeErrorUser("El nombre de la lista ya esta en uso, no se puede repetir. "
                        + "En caso de que el error persista consulte con el admin");
                            break;
                case 1400:  e.setMensajeErrorUser("Debe introducir un dato en los campos obligatorios");
                            break;
                            
                default :   e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
                            break;              
            }
            throw e;
        }
        return registrosAfectados;
    }
    /**
    * Elimina un registro en la tabla Lista
    * @param listaId ID de la lista a eliminar
    * @return Cantidad de registros insertados
    * @throws ExcepcionBP si se produce cualquier error en la inserccion de la lista en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public int eliminarLista(Integer listaId) throws ExcepcionBP{
        conectar();
        int registrosAfectados = 0;      
        String dml = "DELETE FROM LISTA WHERE LISTA_ID="+listaId;

        try {
            
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
            throw e;
        }
        return registrosAfectados;
    }
    /**
    * Modifica un registro en la tabla Lista
    * @param listaId ID de la lista a modificar
    * @param lista Datos de la lista a modificar
    * @return Cantidad de registros insertados
    * @throws ExcepcionBP si se produce cualquier error en la inserccion de la lista en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public int actualizarLista(Integer listaId, Lista lista) throws ExcepcionBP{
        conectar();
        int registrosAfectados = 0;      
        String dml = "UPDATE LISTA set NOMBRE_LISTA=? ,ICONO_LISTA=? "
                + "WHERE LISTA_ID="+listaId;
        try {
            
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            sentenciaPreparada.setString(1, lista.getNombreLista());
            sentenciaPreparada.setString(2, lista.getIconoLista()); 
            
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch(ex.getErrorCode()){
                case 1:     e.setMensajeErrorUser("El nombre de la lista ya esta en uso, no se puede repetir. "
                        + "En caso de que el error persista consulte con el admin");
                            break;
                case 1400:  e.setMensajeErrorUser("Debe introducir un dato en los campos obligatorios");
                            break;
                            
                default :   e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
                            break;  
            }
            
            throw e;
        }
        return registrosAfectados;
    }
    /**
    * Lee un registro en la tabla Lista
    * @param listaId ID de la lista a leer
    * @return Un objeto de tipo Lista
    * @throws ExcepcionBP si se produce cualquier error en la inserccion de la lista en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public Lista leerLista(Integer listaId) throws ExcepcionBP{
        
        conectar();
        Lista l = new Lista();
        String dql = "select * from USUARIO U, LISTA L "
                    + "where U.USUARIO_ID = L.USUARIO_ID and L.LISTA_ID = " + listaId;

        try {
            
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            
            if(resultado.next())
            {
                l.setListaId(resultado.getInt("lista_id"));
                l.setNombreLista(resultado.getString("nombre_lista"));
                l.setIconoLista(resultado.getString("icono_lista"));
                l.setFechaCreacion(resultado.getDate("fecha_creacion"));
                Usuario u = new Usuario();
                u.setUsuarioId(resultado.getInt("usuario_id"));
                u.setNombreUsuario(resultado.getString("nombre_usuario"));
                u.setApellido1(resultado.getString("apellido1"));
                u.setApellido2(resultado.getString("apellido2"));
                u.setCorreo(resultado.getString("correo"));
                u.setContrasena(resultado.getString("contrasena"));
                u.setIconoUsuario(resultado.getString("icono_usuario"));
                u.setAlias(resultado.getString("alias"));
                l.setUsuario(u);     
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
            throw e;
        }
        return l;
    }
    
    /**
    * Lee todos los registros en la tabla Lista
    * @return Un objeto de tipo Lista
    * @throws ExcepcionBP si se produce cualquier error en la inserccion de la lista en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public ArrayList<Lista> leerListas( ) throws ExcepcionBP{
        
        conectar();
        ArrayList<Lista> listas = new ArrayList<>();
        String dql = "select * from LISTA l, USUARIO u "
                + "where l.usuario_id = u.usuario_id";

        try {
            
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            
            while(resultado.next())
            {
                Lista l = new Lista();
                l.setListaId(resultado.getInt("lista_id"));
                l.setNombreLista(resultado.getString("nombre_lista"));
                l.setIconoLista(resultado.getString("icono_lista"));
                l.setFechaCreacion(resultado.getDate("fecha_creacion"));
                Usuario u = new Usuario();
                u.setUsuarioId(resultado.getInt("usuario_id"));
                u.setNombreUsuario(resultado.getString("nombre_usuario"));
                u.setApellido1(resultado.getString("apellido1"));
                u.setApellido2(resultado.getString("apellido2"));
                u.setCorreo(resultado.getString("correo"));
                u.setContrasena(resultado.getString("contrasena"));
                u.setIconoUsuario(resultado.getString("icono_usuario"));
                u.setAlias(resultado.getString("alias"));
                l.setUsuario(u);       
                listas.add(l);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
            throw e;
        }
        return listas;
    }
    
    //------------------------------------------------------------------
    //------------------------------------------------------------------
    //------------------------------------------------------------------
    
    /**
    * Inserta un registro en la tabla Lista
    * @param cancion Datos de la lista a insertar
    * @return Cantidad de registros insertados
    * @throws ExcepcionBP si se produce cualquier error en la inserccion de la lista en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public int insertarCancion(Cancion cancion) throws ExcepcionBP{
        
        conectar();
        int registrosAfectados = 0;
        String dml = "INSERT INTO CANCION (CANCION_ID,NOMBRE_CANCION,DURACION,ARTISTA,ARCHIVO)" +
                    " VALUES (S_CANCION.nextval,?,?,?,?)";        
        try {
                       
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            sentenciaPreparada.setString(1, cancion.getNombre());
            sentenciaPreparada.setString(2, cancion.getDuracion());
            sentenciaPreparada.setString(3, cancion.getArtista());
            sentenciaPreparada.setString(4, cancion.getArchivo());
            
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
                
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()){
                case 1:     e.setMensajeErrorUser("El nombre de la CANCION ya esta en uso, no se puede repetir. "
                        + "En caso de que el error persista consulte con el admin");
                            break;
                case 1400:  e.setMensajeErrorUser("Debe introducir un dato en los campos obligatorios");
                            break;
                            
                default :   e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
                            break;              
            }
            throw e;
        }
        return registrosAfectados;
    }
    /**
    * Elimina un registro en la tabla Lista
    * @param cancionId ID de la lista a eliminar
    * @return Cantidad de registros insertados
    * @throws ExcepcionBP si se produce cualquier error en la inserccion de la lista en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public int eliminarCancion(Integer cancionId) throws ExcepcionBP{
        conectar();
        int registrosAfectados = 0;      
        String dml = "DELETE FROM CANCION WHERE CANCION_ID="+cancionId;

        try {
            
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
            throw e;
        }
        return registrosAfectados;
    }
    /**
    * Modifica un registro en la tabla Lista
    * @param cancionId ID de la lista a modificar
    * @param cancion Datos de la lista a modificar
    * @return Cantidad de registros insertados
    * @throws ExcepcionBP si se produce cualquier error en la inserccion de la lista en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public int actualizarCancion(Integer cancionId, Cancion cancion) throws ExcepcionBP{
        conectar();
        int registrosAfectados = 0;      
        String dml = "UPDATE CANCION set NOMBRE_CANCION=?"
                + "WHERE CANCION_ID="+cancionId;
        try {
            
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            sentenciaPreparada.setString(1, cancion.getNombre());
            
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch(ex.getErrorCode()){
                case 1:     e.setMensajeErrorUser("El nombre de la cancion ya esta en uso, no se puede repetir. "
                        + "En caso de que el error persista consulte con el admin");
                            break;
                case 1400:  e.setMensajeErrorUser("Debe introducir un dato en los campos obligatorios");
                            break;
                            
                default :   e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
                            break;  
            }
            
            throw e;
        }
        return registrosAfectados;
    }
    /**
    * Lee un registro en la tabla Lista
    * @param cancionId ID de la lista a leer
    * @return Un objeto de tipo Lista
    * @throws ExcepcionBP si se produce cualquier error en la inserccion de la lista en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public ArrayList<Cancion> leerCancion(Integer cancionId) throws ExcepcionBP{
        
        conectar();
        ArrayList<Cancion> canciones = new ArrayList<>();
        String dql = "select * from CANCION "
                    + "where CANCION_ID = " + cancionId;

        try {
            
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            
            if(resultado.next())
            {
                Cancion c = new Cancion();
                c.setCancionId(resultado.getInt("cancion_id"));
                c.setNombre(resultado.getString("nombre_cancion"));
                c.setDuracion(resultado.getString("duracion"));
                c.setArtista(resultado.getString("artista"));
                c.setArchivo(resultado.getString("archivo"));
                canciones.add(c);   
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
            throw e;
        }
        return canciones;
    }
    
    /**
    * Lee todos los registros en la tabla Lista
    * @return Un objeto de tipo Lista
    * @throws ExcepcionBP si se produce cualquier error en la inserccion de la lista en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public ArrayList<Cancion> leerCanciones( ) throws ExcepcionBP{
        
        conectar();
        ArrayList<Cancion> canciones = new ArrayList<>();
        String dql = "select * from CANCION ";
                

        try {
            
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            
            while(resultado.next())
            {
                Cancion c = new Cancion();
                c.setCancionId(resultado.getInt("cancion_id"));
                c.setNombre(resultado.getString("nombre_cancion"));
                c.setDuracion(resultado.getString("duracion"));
                c.setArtista(resultado.getString("artista"));
                c.setArchivo(resultado.getString("archivo"));
                canciones.add(c); 
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
            throw e;
        }
        return canciones;
    }
    
    
    //------------------------------------------------------------------
    //------------------------------------------------------------------
    //------------------------------------------------------------------
    
    /**
    * Inserta un registro en la tabla Lista
    * @param lista_Cancion datos de la lista a insertar
    * @return Cantidad de registros insertados
    * @throws ExcepcionBP si se produce cualquier error en la inserccion de la lista en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public int insertarLista_Cancion(Lista_Cancion lista_Cancion) throws ExcepcionBP{
        
        conectar();
        int registrosAfectados = 0;
        String dml = "INSERT INTO LISTA_CANCION (LISTA_ID,CANCION_ID)" +
                    " VALUES (?,?)";        
        try {
                       
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            sentenciaPreparada.setInt(1, lista_Cancion.getLista());
            sentenciaPreparada.setInt(2, lista_Cancion.getCancion());
            
            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();
            conexion.close();
                
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()){
                case 1:     e.setMensajeErrorUser("El nombre de la CANCION ya esta en uso, no se puede repetir. "
                        + "En caso de que el error persista consulte con el admin");
                            break;
                case 1400:  e.setMensajeErrorUser("Debe introducir un dato en los campos obligatorios");
                            break;
                            
                default :   e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
                            break;              
            }
            throw e;
        }
        return registrosAfectados;
    }
    /**
    * Elimina un registro en la tabla Lista
    * @param cancionId ID de la lista a eliminar
    * @return Cantidad de registros insertados
    * @throws ExcepcionBP si se produce cualquier error en la inserccion de la lista en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
//    public int eliminarCancion(Integer cancionId) throws ExcepcionBP{
//        conectar();
//        int registrosAfectados = 0;      
//        String dml = "DELETE FROM CANCION WHERE CANCION_ID="+cancionId;
//
//        try {
//            
//            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
//
//            registrosAfectados = sentenciaPreparada.executeUpdate();
//            sentenciaPreparada.close();
//            conexion.close();
//            
//        } catch (SQLException ex) {
//            ExcepcionBP e = new ExcepcionBP();
//            e.setMensajeErrorAdmin(ex.getMessage());
//            e.setCodigoError(ex.getErrorCode());
//            e.setSentenciaSQL(dml);
//            e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
//            throw e;
//        }
//        return registrosAfectados;
//    }
    /**
    * Modifica un registro en la tabla Lista
    * @param cancionId ID de la lista a modificar
    * @param cancion Datos de la lista a modificar
    * @return Cantidad de registros insertados
    * @throws ExcepcionBP si se produce cualquier error en la inserccion de la lista en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
//    public int actualizarLista_Cancion(Integer cancionId, Lista_Cancion cancion) throws ExcepcionBP{
//        conectar();
//        int registrosAfectados = 0;      
//        String dml = "UPDATE CANCION set NOMBRE_CANCION=?"
//                + "WHERE CANCION_ID="+cancionId;
//        try {
//            
//            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
//
//            sentenciaPreparada.setString(1, cancion.getNombre());
//            
//            registrosAfectados = sentenciaPreparada.executeUpdate();
//            sentenciaPreparada.close();
//            conexion.close();
//            
//        } catch (SQLException ex) {
//            ExcepcionBP e = new ExcepcionBP();
//            e.setMensajeErrorAdmin(ex.getMessage());
//            e.setCodigoError(ex.getErrorCode());
//            e.setSentenciaSQL(dml);
//            switch(ex.getErrorCode()){
//                case 1:     e.setMensajeErrorUser("El nombre de la cancion ya esta en uso, no se puede repetir. "
//                        + "En caso de que el error persista consulte con el admin");
//                            break;
//                case 1400:  e.setMensajeErrorUser("Debe introducir un dato en los campos obligatorios");
//                            break;
//                            
//                default :   e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
//                            break;  
//            }
//            
//            throw e;
//        }
//        return registrosAfectados;
//    }
    /**
    * Lee un registro en la tabla Lista
    * @param cancionId ID de la lista a leer
    * @return Un objeto de tipo Lista
    * @throws ExcepcionBP si se produce cualquier error en la inserccion de la lista en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
//    public Lista_Cancion leerLista_Cancion(Integer cancionId) throws ExcepcionBP{
//        
//        conectar();
//        ArrayList<Cancion> canciones = new ArrayList<>();
//        String dql = "select * from CANCION "
//                    + "where CANCION_ID = " + cancionId;
//
//        try {
//            
//            Statement sentencia = conexion.createStatement();
//            ResultSet resultado = sentencia.executeQuery(dql);
//            
//            if(resultado.next())
//            {
//                Cancion c = new Cancion();
//                c.setCancionId(resultado.getInt("cancion_id"));
//                c.setNombre(resultado.getString("nombre_cancion"));
//                c.setDuracion(resultado.getString("duracion"));
//                c.setArtista(resultado.getString("artista"));
//                c.setArchivo(resultado.getString("archivo"));
//                canciones.add(c);   
//            }
//            resultado.close();
//            sentencia.close();
//            conexion.close();
//            
//        } catch (SQLException ex) {
//            ExcepcionBP e = new ExcepcionBP();
//            e.setMensajeErrorAdmin(ex.getMessage());
//            e.setCodigoError(ex.getErrorCode());
//            e.setSentenciaSQL(dql);
//            e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
//            throw e;
//        }
//        return canciones;
//    }
    
    /**
    * Lee todos los registros en la tabla Lista
    * @return Un objeto de tipo Lista
    * @throws ExcepcionBP si se produce cualquier error en la inserccion de la lista en la base de datos
    * @author Nelson Macedo
    * @version 1.0
    * @since 0.0
    */
    public ArrayList<Lista_Cancion> leerListas_Canciones( ) throws ExcepcionBP{
        
        conectar();
        ArrayList<Lista_Cancion> listas_canciones = new ArrayList<>();
        String dql = "select * from Lista_Cancion ";
                

        try {
            
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            
            while(resultado.next())
            {
                Lista_Cancion lc = new Lista_Cancion();
                lc.setLista(resultado.getInt("lista_id"));
                lc.setCancion(resultado.getInt("cancion_id"));

                listas_canciones.add(lc); 
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            
        } catch (SQLException ex) {
            ExcepcionBP e = new ExcepcionBP();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dql);
            e.setMensajeErrorUser("Error general en el sistema. Consulte con el administrador ");
            throw e;
        }
        return listas_canciones;
    }
}
