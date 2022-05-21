import java.io.Serializable;

public class Email implements Serializable {

    private String email;

    public Email(String mail){
        this.email = mail;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
        return this.email;
    }
}
