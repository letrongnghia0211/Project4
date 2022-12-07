package letrongnghia.aprotrain.foodappproject4.database;


import java.util.Date;

public class User {
    private int UserId;
    private String FullName;
    private String Email;
    private String PassWord;
    private Date Dob;
    private String Gender;
    private String Address;
    private int Phone;

    public User(int userId, String fullName, String email, String passWord, Date dob, String gender, String address, int phone) {
        UserId = userId;
        FullName = fullName;
        Email = email;
        PassWord = passWord;
        Dob = dob;
        Gender = gender;
        Address = address;
        Phone = phone;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public Date getDob() {
        return Dob;
    }

    public void setDob(Date dob) {
        Dob = dob;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }
}
