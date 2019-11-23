
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rumaisa Rabbiya
 */
public class Member implements Serializable {

    private String name;
    private String erp;
    Book book = new Book();
    private String username;
    ArrayList<History> His = new ArrayList();

    Member(String name, String erp, String u) {
        this.name = name;
        this.erp = erp;
        this.username = u;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getname() {
        return name;
    }

    public String geterp() {
        return erp;
    }

    public void checkIn(Book b) throws FileNotFoundException {
        checkHis(b);
        book = null;
    }

    public Catalogue checkOut(Catalogue a, String serial) throws FileNotFoundException {
        if (a.search(serial)) {
            System.out.println("Enterd in CheckOut!");
            book = a.getBook();
            if (book.checker()) {
                book.checkin(this.name, this.erp);
                book.status = false;
                a.b = book;

                a.replace();
                AddHis(book);

                System.out.println("Book Found and Checked Out!");
            } else {
                System.out.println("Book Already Checked Out");
                JOptionPane.showMessageDialog(null, "The Book is Already Checked Out!");
            }
        }
        return a;
    }

    public void bookcheck(Book a) {
        book = a;
    }

    void AddHis(Book b) {
        History h = new History(b);
        His.add(h);
    }

    void checkHis(Book c) {
        for (History h : His) {
            if (h.B.getSerial().equals(c.getSerial()) && h.checkedIn == false) {
                h.CheckIN();
            }
        }
    }

}
