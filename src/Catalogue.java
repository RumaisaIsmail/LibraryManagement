
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rumaisa Rabbiya
 */
public class Catalogue implements Serializable {

    //  ArrayList<Book> book = new ArrayList<>();
    boolean status = false;
    Book b = new Book();

    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    final String filename = "Book Catalogue.ser";
    int index;
    ArrayList<Book> bookOut = new ArrayList<>();
    ArrayList<Book> xx = new ArrayList<>();
    FileInputStream fis = null;
    ObjectInputStream in = null;

    Catalogue() {

    }

    public void Adder(Book a) throws FileNotFoundException {

        ReadFile();
        bookOut.add(a);

        try {
            fos = new FileOutputStream(filename);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(bookOut);

            JOptionPane.showMessageDialog(null, "Done Successfully!");
            oos.close();
            fos.close();
        } catch (IOException ex) {
            System.err.println(ex);
            JOptionPane.showMessageDialog(null, "Couldn't Do.");
        }
    }

    public boolean search(String s) {
        System.out.println("Book Before: " + b);
        b = null;
        System.out.println("Book After: " + b);
        System.out.println("Serial Number serached : "+s);
        boolean status1 = false;
        try {
            ReadFile();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < bookOut.size(); i++) {

            if ((bookOut.get(i).getSerial()).equals(s)) {
                status1 = true;

                b = bookOut.get(i);
                System.out.println("Book Found!" + b.getName());
                index = i;
            }
            if (status1) {
                break;
            }
        }

        return status1;
    }

    public Book getBook() {
        return b;
    }

    public void WriteFile() {

        try {
            fos = new FileOutputStream(filename);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(bookOut);

            JOptionPane.showMessageDialog(null, "Done Successfully!");
            oos.close();
            fos.close();
        } catch (IOException ex) {
            System.err.println(ex);
            JOptionPane.showMessageDialog(null, "Couldn't Do.");
        }

    }

    public void DeleteData(String ser) {
        for (int i = 0; i < bookOut.size(); i++) {
            if ((bookOut.get(i).getSerial()).equals(ser)) {
                bookOut.remove(i);
                //               book = bookOut;
                WriteFile();
            }
        }

    }

    public void replace() throws FileNotFoundException {
        ReadFile();
        bookOut.set(index, b);
        WriteFile();
//        try {
//            fos = new FileOutputStream(filename);
//            oos = new ObjectOutputStream(fos);
//
//            oos.writeObject(bookOut);
//            System.out.println("write");
//
//            oos.close();
//            fos.close();
//            JOptionPane.showMessageDialog(null, "Done Successfully!");
//
//        } catch (IOException ex) {
//            System.err.println(ex);
//            JOptionPane.showMessageDialog(null, "Couldn't Do.");
//        }

    }

    public void ReadFile() throws FileNotFoundException {
        try {

            fis = new FileInputStream("Book Catalogue.ser");
            in = new ObjectInputStream(fis);
            bookOut = (ArrayList) in.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File Not Found");
        }
        //       book = bookOut;
    }

    public ArrayList booklistcheckout(String erp) throws FileNotFoundException {
        ReadFile();
        for (int i = 0; i < bookOut.size(); i++) {

            if ((bookOut.get(i).geterp()).equals(erp)) {
                b = bookOut.get(i);
                xx.add(b);
            }

        }
        return xx;

    }
}
