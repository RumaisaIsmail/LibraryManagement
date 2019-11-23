/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rumaisa Rabbiya
 */
public class ManagementSystem {
   private final String pass="123";
    private final String username="Manager";
    
    private String AccountU="Admin";
    private String AccountP="123";
    
    
    
    public String getPass(){
        return pass;
    
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setInchargeU(String x){
        AccountU= x;
    }
    
    public void setInchargeP(String x){
        AccountP= x;
    }
    public String getInchargeU(){
        return AccountU;
    }
     public String getInchargeP(){
        return AccountP;
    }
    
    
}
