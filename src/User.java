import java.io.Serial;
import java.io.Serializable;

/**
 * <h1>User</h1>
 * The <u>abstract class</u> User defines the commons elements to all the users
 * of the app. It must be extended to create new types of user.*/
public abstract class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 344120892;

    /* The first name of the user */
    protected String firstName;

    /* The last name of the user */
    protected String lastName;

    /* The e-mail of the user */
    protected Email email;

    /* The password of the user */
    protected String password;

    /**
     * Create a new {@link User}.*/
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

    /**
     * Must be used for loading an existing {@link User}. This method affects the value
     * of e-mail without modifying the database.*/
    public User(Email mail){
        email = mail;
    }

    public User(){}

    /**
     * @return the first name of a {@link User}.*/
    public String getFirstName(){
        return firstName;
    }

    /**
     * @return the last name of a {@link User}.*/
    public String getLastName(){
        return lastName;
    }

    /**
     * @return the e-mail of a {@link User}.*/
    public Email getEmail(){
        return email;
    }

    /**
     * @return the password of a {@link User}.*/
    public String getPassword(){
        return password;
    }

    public void setFirstName(String newFirstName){
        firstName = newFirstName;
    }

    public void setLastName(String newLastName){
        lastName = newLastName;
    }

    /**
     * Modify the e-mail of a {@link User}. The user's file is modified
     * in the database.*/
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
            System.out.println("Mail déjà utilisé, veuillez en choisir un autre");
        }
    }

    public void setPassword(String newPassword){
        password = newPassword;
    }

    public boolean connect(String mail, String pw){
        return true;
    }

    /**
     * Save a {@link User}. on hard drive in the database.*/
    public void save(){
        FileHelper.export("AppDataBase/UsersFiles.santaDB/" + email.toString(),this);
    }
}