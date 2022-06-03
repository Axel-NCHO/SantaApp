import javax.swing.*;

/**
 * <h1>AccountCreationThread</h1>
 * Thread for creating an account when the 'submit' button
 * on {@link RegistrationPage} is clicked.*/
public class AccountCreationThread extends Thread {

    /* The registration page */
    private final RegistrationPage page;

    /* The new child created */
    private Child child;

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
        if (mail.isValidEmail() && !page.getFirstName().getText().equals("") && !page.getLastName().getText().equals("")) {
            Child newChild = new Child(page.getFirstName().getText(), page.getLastName().getText(), mail, page.getPassword().getText(), Integer.parseInt(page.getAge().getText()), page.getAddress(), Gentleness.EXCELLENT_BOY);
            JOptionPane.showMessageDialog(page, "Compte créé avec succès !");
            this.child = newChild;
        } else {
            JOptionPane.showMessageDialog(page, "Erreur. Vérifies ta saisie", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Start the thread.
     * @return the {@link Child} object created.*/
    public Child startThread(){
        this.start();
        return this.child;
    }

}

