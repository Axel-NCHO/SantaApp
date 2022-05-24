import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountCreationThread extends Thread {

    private RegistrationPage page;
    private boolean exitSatus;


    public AccountCreationThread(RegistrationPage page) {
        this.page = page;
    }

    public void run() {
        Email mail = new Email(page.getEmail().getText());
        if (mail.isValid() && page.getFirstName().getText() != "" && page.getLastName().getText() != "") {
            Child newChild = new Child(page.getFirstName().getText(), page.getLastName().getText(), mail, page.getPassword().getText(), page.getDateOfBirth(), page.getAddress(), (int) (Math.random() * 15));
            JOptionPane.showMessageDialog(page, "Compte créé avec succès !");
            System.out.println("Compte créé pour " + page.getEmail().getText());
            this.exitSatus = true;
        } else {
            this.exitSatus = false;
            JOptionPane.showMessageDialog(page, "Erreur. Vérifies ta saisie", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean startThread(){
        run();
        return this.exitSatus;
    }

}

