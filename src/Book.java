
import java.io.Serializable;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rumaisa Rabbiya
 */
public class Book implements Serializable{
    private String name;
    private String serial;
    private String author;
    
    boolean status=true;
    private String Cname;
    private String ERP;
   
    private String type;
    
    
    
    Book(){
    }
    
    Book(String name, String s, String a, String type){
        this.name= name;
        serial=s;
        author=a;
        
        this.type=type;
    }
    
    public String getName(){
        return name;
    }
    
     public String getAuthor(){
        return author;
    }
     
      public String getSerial(){
        return serial;
    }
    

    public void setserial(String a){
       
        
        serial=a;
        
    }
     public void setname(String a){
       
        name=a;
        
    }
     public void setauthor(String a){
        
        author=a;
        
    }
     
     public void settype(String a){
        
        type=a;
        
    }
     
     public boolean checker(){
         return status;
     }
     
     
    public void checkin(String name, String erp){
       
         Cname= name;
         ERP= erp;
         status=true;
       }
    public void checkout(String name, String erp){
       
         Cname= name;
         ERP= erp;
         status=false;
       }
    
    public String geterp(){
        return ERP;
    }

    public String getCname() {
        return Cname;
    }
    
}
