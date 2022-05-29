import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email implements Serializable {

    private String email;

    public Email(String mail){
        this.email = mail;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValidEmail(){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.email);

        return matcher.matches();
    }
    @Override
    public boolean equals (Object obj) {
        if (obj == null) {return false;}
        if (obj == this) {return true;}
        if (!(obj instanceof Email)) {return false;}
        Email mail = (Email) obj;
        return mail.toString().equals(this.email);
    }

    public String toString(){
        return this.email;
    }
}
