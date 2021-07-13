
import java.util.ArrayList;

public class Author {
    private int id;
    private String name, university, department, mail;
    public ArrayList<Integer> bookNum= new ArrayList<>();

    public Author(int id, String name, String university, String department, String mail){
        this.id = id;
        this.name = name;
        this.university = university;
        this.department = department;
        this.mail = mail;
    }
    public Author(int id){
        this.id = id;
        name = " ";
        university = " ";
        department = " ";
        mail = " ";
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ArrayList<Integer> getBookNum() {
        return bookNum;
    }

    public void setBookNum(ArrayList<Integer> bookNum) {
        this.bookNum = bookNum;
    }

    public void addNumber(int num){
        bookNum.add(num);
    }
    public String list(){
        return "Author:"+getId()+"\t"+getName()+"\t"+getUniversity()+"\t"+getDepartment()+"\t"+getMail();
    }
}
