import java.io.Serializable;

public abstract class User implements Serializable {
    private static final long serialVersionUID = 344120892;	
    private String firstName;
    private String lastName;
    private Email email;
    private String password;

    public User(String fn, String ln, Email mail, String pw){
        String[] users = FileHelper.fileList("AppDataBase/UsersFiles.santaDB");
        int i = 0;
        while(i < users.length && !users[i].equals(mail.toString())){
            i ++;
        }
        if(i >= users.length) {
            firstName = fn;
            lastName = ln;
            email = mail;
            password = pw;
        }
    }

    public User(Email mail){
        email = mail;
    }

    public User(){}

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
        String[] users = FileHelper.fileList("AppDataBase/UsersFiles.santaDB");
        int i = 0;
        while(i < users.length && !users[i].equals(newEmail)){
            i ++;
        }
        if(i >= users.length){
            if(email == null){
                email = new Email(newEmail);
            }
            else{
                email.setEmail(newEmail);
            }

        }
        else{
            System.out.println("Mail déjà utilisé, veuillez en choisir un autre");;
        }
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