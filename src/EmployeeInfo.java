
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rumaisa Rabbiya
 */
public class EmployeeInfo implements Serializable {

    private String name;
    private String erp;
    private String UserName;

    EmployeeInfo(String name, String erp, String u) {
        this.name = name;
        this.erp = erp;
        this.UserName = u;
    }

    public String getname() {
        return name;
    }

    public String geterp() {
        return erp;
    }

    public String getUserName() {
        return UserName;
    }

}
