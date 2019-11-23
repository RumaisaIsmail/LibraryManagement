
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rumaisa Rabbiya */
public class AccountsSettings implements Serializable {

    Map<String, String> AccountE = new HashMap<>();
    Map<String, String> AccountM = new HashMap<>();
    ArrayList<Member> memberinfo = new ArrayList<>();
    ArrayList<EmployeeInfo> employeeinfo = new ArrayList<>();

    AccountsSettings() {

    }

    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    final String Employee = "Employee Account.ser";
    final String Employeeinfo = "Employee Information.ser";
    final String Memberinfo = "Member Information.ser";
    final String Member = "Member Account.ser";
    static int i = 0;
    Member m;
    EmployeeInfo e;
    FileInputStream fis = null;
    int im;
    ObjectInputStream in = null;

    public boolean searchemployee(String name, String pass) throws FileNotFoundException {
        boolean a = false;

        ReadE();
        ReadEinfo();
        for (Map.Entry<String, String> entry : AccountE.entrySet()) {
            if (entry.getKey().equals(name) && entry.getValue().equals(pass)) {
                a = true;
                break;
            }
        }
        return a;
    }

    public boolean serE(String user) {
        boolean a = false;
        for (Map.Entry<String, String> entry : AccountE.entrySet()) {
            if (entry.getKey().equals(user)) {
                a = true;
                break;
            }
        }

        return a;
    }

    public boolean serMem(String u) throws FileNotFoundException {
        boolean a = false;

        ReadMinfo();
        for (int j = 0; j < memberinfo.size(); j++) {
            if (memberinfo.get(j).getUsername().equals(u)) {
                a = true;
                m = memberinfo.get(j);
                im = j;
                break;
            }

        }
        return a;
    }

    public boolean serM(String u, String s) throws FileNotFoundException {
        boolean a = false;

        ReadMinfo();
        for (int j = 0; j < memberinfo.size(); j++) {
            if (memberinfo.get(j).getname().equals(u) && memberinfo.get(j).geterp().equals(s)) {
                a = true;
                m = memberinfo.get(j);
                break;
            }

        }
        return a;
    }

    public void replaceM() throws FileNotFoundException {
        ReadMinfo();
        memberinfo.set(im, m);
        WriteMinfo();
    }

    public boolean searchmember(String name, String pass) throws FileNotFoundException {
        boolean a = false;
        ReadM();
        ReadMinfo();
        for (Map.Entry<String, String> entry : AccountM.entrySet()) {
            if (entry.getKey().equals(name) && entry.getValue().equals(pass)) {

                if (serMem(name)) {
                    a = true;
                    break;
                }

            }
            if (a == true) {
                break;
            }

        }
        return a;
    }

    public boolean AccountEAdder(String u, String pass, String name, String erp) throws FileNotFoundException {

        ReadE();

        if (AccountE.size() <= 3) {
            newE(name, erp, u);
            AccountE.put(u, pass);
            WriteE();
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "NO more entries allowed");
            return false;
        }
    }

    public void AccountEDeleter(String name, String erp) throws FileNotFoundException {
        ReadE();
        if (serE(erp)) {
            deleteE(name);
            System.out.println(AccountE.size());
            AccountE.remove(erp);
            System.out.println(AccountE.size());
        } else {
            JOptionPane.showMessageDialog(null, "Please Enter a Valid User Name!");
        }
        WriteE();
        i--;
    }

    public void AccountMAdder(String name, String pass) throws FileNotFoundException {
        ReadM();
        AccountM.put(name, pass);
        WriteM();
    }

    public void AccountMDeleter(String name, String erp) throws FileNotFoundException {
        ReadM();
        DeleteM(name);
        AccountM.remove(erp);
        WriteM();
    }

    public void newE(String name, String erp, String u) throws FileNotFoundException {
        ReadEinfo();
        employeeinfo.add(new EmployeeInfo(name, erp, u));
        WriteEinfo();
    }

    public void deleteE(String erp) throws FileNotFoundException {
        ReadEinfo();
        for (int i = 0; i < employeeinfo.size(); i++) {
            if ((employeeinfo.get(i).getname()).equals(erp)) {
                employeeinfo.remove(i);

                WriteEinfo();
            }
        }
    }

    public void newM(String name, String erp, String u) throws FileNotFoundException {
        ReadMinfo();
        memberinfo.add(new Member(name, erp, u));
        WriteMinfo();
    }

    public void DeleteM(String erp) throws FileNotFoundException {
        ReadMinfo();
        for (int i = 0; i < memberinfo.size(); i++) {
            if ((memberinfo.get(i).getname()).equals(erp)) {
                memberinfo.remove(i);

                WriteMinfo();
            }
        }
    }

    public void WriteEinfo() {
        try {
            fos = new FileOutputStream(Employeeinfo);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(employeeinfo);

            oos.close();
            fos.close();
        } catch (IOException ex) {
            System.err.println(ex);

        }
    }

    public void WriteE() {
        try {
            fos = new FileOutputStream(Employee);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(AccountE);

            oos.close();
            fos.close();
        } catch (IOException ex) {
            System.err.println(ex);

        }
    }

    public void WriteMinfo() {

        try {
            fos = new FileOutputStream(Memberinfo);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(memberinfo);

            oos.close();
            fos.close();
        } catch (IOException ex) {
            System.err.println(ex);

        }
    }

    public void WriteM() {
        try {
            fos = new FileOutputStream(Member);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(AccountM);

            oos.close();
            fos.close();
        } catch (IOException ex) {
            System.err.println(ex);

        }
    }

    public void ReadE() throws FileNotFoundException {
        try {

            fis = new FileInputStream("Employee Account.ser");
            in = new ObjectInputStream(fis);
            AccountE = (Map) in.readObject();
            in.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File Not Found");
        } catch (IOException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File Not Found");
        }
    }

    public void ReadEinfo() throws FileNotFoundException {
        try {

            fis = new FileInputStream("Employee Information.ser");
            in = new ObjectInputStream(fis);
            employeeinfo = (ArrayList) in.readObject();
            in.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File Not Found");
        } catch (IOException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File Not Found");
        }
    }

    public void ReadM() throws FileNotFoundException {
        try {

            fis = new FileInputStream("Member Account.ser");
            in = new ObjectInputStream(fis);
            AccountM = (Map) in.readObject();
            in.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File Not Found");
        } catch (IOException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File Not Found");
        }
    }

    public void ReadMinfo() throws FileNotFoundException {
        try {

            fis = new FileInputStream("Member Information.ser");
            in = new ObjectInputStream(fis);
            memberinfo = (ArrayList) in.readObject();
            in.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File Not Found");
        } catch (IOException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File Not Found");
        }
    }

    public void bookcheckoutM(Book s, String name, String erp) {

        for (int i = 0; i < memberinfo.size(); i++) {
            if (memberinfo.get(i).geterp().equals(erp)) {

                if (s.checker()) {
                    s.checkin(name, erp);
                }

            }
        }

    }

    public void bookcheckoutE(Book s, String name, String erp) {

        for (int i = 0; i < employeeinfo.size(); i++) {
            if (employeeinfo.get(i).geterp().equals(erp)) {

                if (s.checker()) {
                    s.checkin(name, erp);
                }

            }
        }

    }
}
