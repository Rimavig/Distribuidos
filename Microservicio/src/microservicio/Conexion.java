/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservicio;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import net.spy.memcached.MemcachedClient;
/**
 *
 * @author RICHARD
 */

public class Conexion {
   
   
   private  Connection cnx = null;
   
   public  LinkedList<Noticia> obtenerTop10()  {
         
       try {
            Class.forName("com.mysql.jdbc.Driver");
            //Conexion con la base de datos, usuario y clave
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/newspaper", "root", "hotmail003");
            //se establece la conexion
            Statement st= cnx.createStatement();
            System.out.println("Se establecio con exito la conexion");
            //Parametros para consulta de la base de datos 
            PreparedStatement consulta = cnx.prepareStatement("SELECT id,visitas,publisher,title FROM noticias" );
            //contiene todo el resultado de la consulta 
            ResultSet resultado = consulta.executeQuery();
             LinkedList<Noticia> lista =new  LinkedList<Noticia>();
            Noticia not;
            //recorremos el resultado de la consulta de la base de datos
            while(resultado.next()){
                not=new Noticia();
                //guardamos en una Noticia cada parametro 
                not.setId(resultado.getInt("id"));
                not.setVisitas(resultado.getInt("visitas"));
                not.setPublisher(resultado.getString("publisher"));
                not.setTitle(resultado.getString("title"));
                lista.add(not);
            }
            //Ordenamos la lista de noticias segun las visitas de mayor a menor 
            Collections.sort(lista);
            //Obtenemos el top 10 de las noticias de la base de datos 
           LinkedList<Noticia> top10 =new  LinkedList<Noticia>();
            for (int i = 0; i < 10; i++) {
               top10.add(lista.get(i));
           }
           
            return top10;
            
         } catch (Exception ex) {
             System.out.println("No se pudo conectar a la Base de datos");
         }
       return null;
   }
   public LinkedList<Noticia> guardarCache()  {
         //El puerto default de memcached es 11211
        InetSocketAddress[] servers = new InetSocketAddress[]{ new InetSocketAddress("127.0.0.1", 11211)};
        MemcachedClient mc;
        try {
            mc = new MemcachedClient(servers);
           LinkedList<Noticia> noticias=null;
            //Cuando pase una hora, esto nos devolverá null
            if (mc.get("noticias")==(null)){
                LinkedList<Noticia> lista=obtenerTop10();
                //Así almacenamos un valor
                //se pasa llave, duración en segundos, valor
                mc.set("noticias", 86400, lista);
                //Lo siguiente funcionará durante una hora
                noticias = (LinkedList<Noticia>)mc.get("noticias");
            }else{
                noticias = (LinkedList<Noticia>)mc.get("noticias");
            }
          
            mc.shutdown();
            return noticias;
        } catch (IOException ex) {
            System.out.println("ERROR");
        }
       return null;
   }
}
