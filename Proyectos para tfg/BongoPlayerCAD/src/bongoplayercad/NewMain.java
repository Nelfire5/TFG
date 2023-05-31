/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bongoplayercad;

import bongoplayerpojo.Cancion;
import bongoplayerpojo.ExcepcionBP;
import bongoplayerpojo.Lista;
import bongoplayerpojo.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author usuario
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BongoPlayerCAD cad;
        int ra=0;
        
        Lista l = new Lista(null, "piripi", null, null,new Usuario(4));
        Usuario u = new Usuario(null, "x", "x", null, "as@gmail.com", "11", null, "Don-Jai");
        Cancion c = new Cancion(null, "xx", 3.3, "xx", null);
        
        try {            
           cad = new BongoPlayerCAD();
            ra = cad.actualizarCancion(8, c);
            //ra = cad.insertarCancion(c);  
           //System.out.println(cad.leerCancion(1));
            ra = cad.eliminarCancion(7);
//            for(int i=0; i < cad.leerCanciones().size();i++)
//            {
//                System.out.println(cad.leerCanciones().get(i));
//            }

        } catch (ExcepcionBP ex) {
            System.out.println(ex.getMensajeErrorUser());
            System.out.println(ex);
        }

//        try {            
//            cad = new BongoPlayerCAD();
//           ra = cad.insertarLista(l);
//            System.out.println(cad.leerLista(4));
//            ra = cad.actualizarLista(23, l);
//            ra = cad.eliminarLista(23);
//            for(int i=0; i < cad.leerListas().size();i++)
//            {
//                System.out.println(cad.leerListas().get(i));
//            }
//
//        } catch (ExcepcionBP ex) {
//            System.out.println(ex.getMensajeErrorUser());
//            System.out.println(ex);
//        }
//        
//        System.out.println("Registros afectados: " + ra);
        
    }
}
