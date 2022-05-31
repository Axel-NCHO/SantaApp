import javax.swing.*;

public class AccountCreationThread extends Thread {

    private RegistrationPage page;
    private Child child;

    public AccountCreationThread(RegistrationPage page) {
        this.page = page;
    }

    public void run() {
        Email mail = new Email(page.getEmail().getText());
        if (mail.isValidEmail() && page.getFirstName().getText() != "" && page.getLastName().getText() != "") {
            Child newChild = new Child(page.getFirstName().getText(), page.getLastName().getText(), mail, page.getPassword().getText(), Integer.parseInt(page.getAge().getText()), page.getAddress(), Gentleness.EXCELLENT_BOY);
            JOptionPane.showMessageDialog(page, "Compte créé avec succès !");
            this.child = newChild;
        } else {
            JOptionPane.showMessageDialog(page, "Erreur. Vérifies ta saisie", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Child startThread(){
        run();
        return this.child;
    }

}

