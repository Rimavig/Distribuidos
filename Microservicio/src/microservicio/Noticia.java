/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservicio;

import java.io.Serializable;

/**
 *
 * @author RICHARD
 */
public class Noticia implements Comparable<Noticia>, Serializable {
   private int id;
   private int visitas;
   private String title;
   private String publisher;
   

    public Noticia() {
    }

    public Noticia(int id, int visitas, String title, String publisher) {
        this.id = id;
        this.visitas = visitas;
        this.title = title;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public int compareTo(Noticia o) {
       if (visitas<o.visitas){
           return 1;
       }
       if (visitas>o.visitas){
           return -1;
       }
       return 0;
    }

    @Override
    public String toString() {
        return  id + "," + visitas + "," + title + "," + publisher ;
    }
    
}
