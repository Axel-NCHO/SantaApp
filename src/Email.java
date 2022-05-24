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

    public boolean isValid(){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.email);

        return matcher.matches();
    }

    public String toString(){
        return this.email;
    }
}
