package letrongnghia.aprotrain.foodappproject4.database;




public class UserAdmin {
    private int UserId;
    private String FullName;
    private String Email;
    private String PassWord;

    private String Phone;

    public  UserAdmin(String editTextFullName, String editTextEmail, String editTextPassWord, String editTextPhone){
        this.FullName = editTextFullName;
        this.Email = editTextEmail;
        this.PassWord = editTextPassWord;
        this.Phone = editTextPhone;
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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
