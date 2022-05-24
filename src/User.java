import java.io.File;
import java.io.Serializable;

public abstract class User implements Serializable {
    private String firstName;
    private String lastName;
    private Email email;
    private String password;

    public User(String fn, String ln, Email mail, String pw){
        String[] users = new File("AppDataBase/UsersFiles.santaDB").list();
        int i = 0;
        while(i < users.length && !users[i].equals(mail.toString())){
            i ++;
        }
        if(i >= users.length) {
            firstName = fn;
            lastName = ln;
            email = mail;
            password = pw;
            this.save();
        }

    }

    public User(Email mail){
        email = mail;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public Email getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setFirstName(String newFirstName){
        firstName = newFirstName;
    }

    public void setLastName(String newLastName){
        lastName = newLastName;
    }

    public void setEmail(String newEmail){
        email.setEmail(newEmail);
    }

    public void setPassword(String newPassword){
        password = newPassword;
    }

    public boolean connect(String mail, String pw){
        return true;
    }

    public void save(){
        FileHelper.export("AppDataBase/UsersFiles.santaDB/" + email.toString(),this);
    }

}