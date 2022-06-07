import javax.swing.*;
import java.util.ArrayList;

/**
 * <h1>AccountCreationThread</h1>
 * Thread for creating an account when the 'submit' button
 * on {@link RegistrationPage} is clicked.*/
public class AccountCreationThread extends Thread {

    /* The registration page */
    private final RegistrationPage page;

    /* The new child created */
    private User user;
    /**
     * Creates a thread for creating a new account.*/
    public AccountCreationThread(RegistrationPage page) {
        this.page = page;
    }

    /**
     * Check if the e-mail is valid and if first and last names are given.
     * If so, create the account and show notification.
     * If not, show notification.*/
    public void run() {
        Email mail = new Email(page.getEmail().getText());
        Boolean emailExists = true;
        String[] users = FileHelper.fileList("AppDataBase/UsersFiles.santaDB");
        int i = 0;
        while(i < users.length && !users[i].equals(mail.toString())){
            i ++;
        }
        if(i >= users.length) {
            emailExists = false;
        }
        else{
            JOptionPane.showMessageDialog(page, "Erreur. Email déjà utilisé, essayez avec une autre adresse mail.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        if (mail.isValidEmail() && !page.getFirstName().getText().equals("") && !page.getLastName().getText().equals("") && !emailExists) {
            if (mail.toString().endsWith("@pelf.com")) {
                PackagingElf packagingElf = new PackagingElf(page.getFirstName().getText(), page.getLastName().getText(), mail, page.getPassword().getText(), EmploymentStatus.RECRUITED);
                JOptionPane.showMessageDialog(page, "Compte créé avec succès !");
                this.user = packagingElf;
            } else {
                Gentleness gentleness = chooseRandomGentleness();
                Child newChild = new Child(page.getFirstName().getText(), page.getLastName().getText(), mail, page.getPassword().getText(), Integer.parseInt(page.getAge().getText()), page.getAddress(), gentleness);
                JOptionPane.showMessageDialog(page, "Compte créé avec succès !");
                this.user = newChild;
            }
        } else {
            JOptionPane.showMessageDialog(page, "Erreur. Vérifies ta saisie", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Gentleness chooseRandomGentleness() {
        ArrayList<Gentleness> gentlenesses = new ArrayList<Gentleness>();
        gentlenesses.add(Gentleness.EXCELLENT_BOY);
        gentlenesses.add(Gentleness.NICE_BOY);
        gentlenesses.add(Gentleness.GOOD_BOY);
        gentlenesses.add(Gentleness.BAD_BOY);

        int choosenIndice = (int)(Math.random() * 3);
        return gentlenesses.get(choosenIndice);
    }

    /**
     * Start the thread.
     * @return the {@link Child} object created.*/
    public User startThread(){
        this.run();
        return this.user;
    }

}

