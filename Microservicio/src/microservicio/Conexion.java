/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservicio;
import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
/**
 *
 * @author RICHARD
 */

public class Conexion {
   
   
   private  Connection cnx = null;
   
   public  LinkedList<Noticia> obtener()  {
         
       try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/newspaper", "root", "hotmail003");
            Statement st= cnx.createStatement();
            System.out.println("Se establecio con exito la conexion");
            PreparedStatement consulta = cnx.prepareStatement("SELECT id,visitas,publisher,title FROM noticias" );
            ResultSet resultado = consulta.executeQuery();
             LinkedList<Noticia> lista =new  LinkedList<Noticia>();
            Noticia not;
            
            while(resultado.next()){
                not=new Noticia();
                not.setId(resultado.getInt("id"));
                not.setVisitas(resultado.getInt("visitas"));
                not.setPublisher(resultado.getString("publisher"));
                not.setTitle(resultado.getString("title"));
                   lista.add(not);
            }
            Collections.sort(lista);
            
           LinkedList<Noticia> top10 =new  LinkedList<Noticia>();
            for (int i = 0; i < 10; i++) {
               top10.add(lista.get(i));
           }
           
            return top10;

         } catch (SQLException ex) {
             System.out.println("No se pudo conectar a la Base de datos");
         } catch (Exception ex) {
             System.out.println("Error");;
         }
       return null;
   }
}
