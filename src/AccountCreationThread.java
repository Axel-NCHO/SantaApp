import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountCreationThread extends Thread {

    private UITextField lastName;
    private UITextField firstName;
    private UITextField email;
    private UITextField age;
    private UITextField pwd;
    private UITextField dateOfBirth;
    private UITextField city ;
    private UITextField country;


    public AccountCreationThread(UITextField fname, UITextField lname, UITextField email, UITextField pwd, UITextField dateOfBirth, UITextField age, UITextField city, UITextField country){
        this.firstName = fname;
        this.lastName = lname;
        this.email = email;
        this.pwd = pwd;
        this.age = age;
        this.city = city;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
    }

    public void run(){
        Email mail = new Email(email.getText());
        if(mail.isValid() && firstName.getText() != "" && lastName.getText() != ""){
            Child newChild = new Child(firstName.getText(), lastName.getText(), mail, pwd.getText(), getDateOfBirth(), getAddress(), (int) (Math.random()*15));
            //Pop-up
            System.out.println("Compte créé pour " + email.getText());
        } else {
            //Pop-up!!!!!!!!!!!!!!
            System.out.println("Veuillez vérifier vos entrées");
        }
    }

    public Date getDateOfBirth(){
        if (this.dateOfBirth.getText() != "") {
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(this.dateOfBirth.getText());
            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }

    public String getAddress(){
        return age.getText() + " " + city.getText() + ", " + country.getText();
    }
}
