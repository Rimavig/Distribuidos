/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservicio;

import Thrift.Servidor;
import java.util.LinkedList;
import org.apache.thrift.TException;






/**
 *
 * @author RICHARD
 */
public class Microservicio implements Servidor.Iface {

    /**
     * @param args the command line arguments
     */
    
    private static LinkedList<Noticia> noticias;
    public static void main(String[] args) {
//        Conexion con =new Conexion();
//        noticias=con.guardarCache();
//        for(Noticia noticia: noticias){
//            System.out.println(noticia);
//       }
        HiloServidor hs = new HiloServidor();
        Thread t = new Thread(hs);
        t.start();
    }

    @Override
    public String top10(String dato1) throws TException {
        Conexion con =new Conexion();
        noticias=con.guardarCache();
        for(Noticia noticia: noticias){
            System.out.println(noticia);
       } 
        String top="";
        for(Noticia noticia: noticias){
            top=top+noticia+";";
        }
        return top;       
    }

    @Override
    public String optenerLista() throws TException {
        return "dsada";
    }
        
    
}
