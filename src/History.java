
import java.io.Serializable;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rumaisa Rabbiya
 */
public class History implements Serializable{

    Book B;
    boolean checkedIn;
    boolean checkedOut;
    Date checkInDate;
    Date checkOutDate;

    History(Book k) {
        B = k;
        checkedOut =true;
        checkOutDate = new Date();
        System.out.println(checkOutDate.toString());
        B.status = false;
    }
    
    void CheckIN(){
        checkInDate = new Date();
        checkedIn = true;
        checkedOut = false;
        System.out.println(checkInDate.toString());
        B.status = true;
    }
    
   
}
