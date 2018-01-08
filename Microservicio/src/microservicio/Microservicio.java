/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservicio;

import java.util.LinkedList;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.spy.memcached.MemcachedClient;




/**
 *
 * @author RICHARD
 */
public class Microservicio {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        
        //El puerto default de memcached es 11211
        InetSocketAddress[] servers = new InetSocketAddress[]{ new InetSocketAddress("127.0.0.1", 11211)};
        MemcachedClient mc;
        try {
            mc = new MemcachedClient(servers);
           LinkedList<Noticia> noticias=null;
            //Cuando pase una hora, esto nos devolverá null
//            valor = (String)mc.get("acum");
            if (mc.get("noticias")==(null)){
                Conexion con=new Conexion();
                LinkedList<Noticia> lista=con.obtener();
                //Así almacenamos un valor
                //se pasa llave, duración en segundos, valor.
                mc.set("noticias", 3600, lista);
                //Lo siguiente funcionará durante una hora
                noticias = (LinkedList<Noticia>)mc.get("noticias");
            }else{
                noticias = (LinkedList<Noticia>)mc.get("noticias");
            }
           for(Noticia noticia: noticias){
                System.out.println(noticia);
            }
            mc.shutdown();
            
        } catch (IOException ex) {
            System.out.println("ERROR");
        }
        
    }
        
    
}
