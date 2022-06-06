import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1>UIActionListener</h1>
 * <hr>
 * The main action listener of the app. It gathers the actions performed by all the components
 * of the app that need an {@link ActionListener}.
 * It performs different actions depending on where it is called.*/
public class UIActionListener implements ActionListener {

    private final UIPage page;

    public UIActionListener(UIPage page){
        this.page = page;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /* Action performed if the calling component is on a registration page */
        if (this.page instanceof RegistrationPage){
            switch (e.getActionCommand()) {
                case "Soumettre" :
                    AccountCreationThread thread = new AccountCreationThread((RegistrationPage) this.page);
                    User user = thread.startThread();
                    if (user != null) {
                        if (user instanceof Child) {
                            Child child = (Child) user;
                            new NewChildThread(child).start();
                        } else {
                            new ConnexionPage().run();
                        }
                        new CloseWindowThread(page).start();
                    }
                    break;
                case "Je me connecte !" :
                    new Thread() {
                        @Override
                        public void run() {
                            ConnexionPage connexionPage = new ConnexionPage();
                            connexionPage.run();
                        }
                    }.start();
                    new CloseWindowThread(page).start();
                    break;
            }
        }

        /* Action performed if the calling component is on a NewChildHomePage */
        if (this.page instanceof NewChildHomePage) {
            OrderSubmissionThread thread = new OrderSubmissionThread((NewChildHomePage) this.page);
            Order order = thread.startThread();
            if(order != null){
                new OrderCheckingThread(order).start();
                new CloseWindowThread(page).start();
            }
        }

        /* Action performed if the calling component is on a connection page */
        if (this.page instanceof ConnexionPage) {
            if (e.getActionCommand().equals("Je crée un compte !")) { // The user wants to create an account
                new Thread(){
                    public void run() {
                        RegistrationPage registrationPage = new RegistrationPage();
                        registrationPage.run();
                    }
                }.start();
                new CloseWindowThread(this.page).start();
            } else { // The user wants to connect itself to his/her account
                ConnexionThread thread = new ConnexionThread((ConnexionPage) this.page);
                thread.start();
            }
        }

        /* Action performed if the calling component is on an ExistingChildHomePage */
        if (page instanceof ExistingChildHomePage) {
            // On veut pouvoir re-afficher NewChildHomePage mais contenant déjà la commande de l'enfant
            // ExistingChildHomePage page = (ExistingChildHomePage) this.page;
            // ...
            JOptionPane.showMessageDialog(page, "Cette fonctionnalité n'est pas encore disponible.", "Désolé !", JOptionPane.ERROR_MESSAGE);
        }

        /* Action performed if the calling component is on a SantaHomePage */
        if (this.page instanceof SantaHomePage) {
            SantaHomePage page = (SantaHomePage) this.page;
            // Menu buttons
            switch (e.getActionCommand()) {
                case "Commandes en attente de validation" :
                    page.getMainCardLayout().show(page.getContentPane(), "1");
                    break;
                case "Commandes validées" :
                    page.getMainCardLayout().show(page.getContentPane(), "2");
                    break;
                case "Commandes prêtes" :
                    page.getMainCardLayout().show(page.getContentPane(), "3");
                    break;
                case "Lutins préparateurs" :
                    page.getMainCardLayout().show(page.getContentPane(), "4");
                    break;
                case "Lutins soigneurs" :
                    page.getMainCardLayout().show(page.getContentPane(), "5");
                    break;
                case "Gestion des rennes" :
                    page.getMainCardLayout().show(page.getContentPane(), "6");
                    break;

                case "Précédent" :
                    page.getCardLayoutForContentPanel().previous(page.getContentPanel());
                    break;
                case "Suivant" :
                    page.getCardLayoutForContentPanel().next(page.getContentPanel());
                    break;
                case "Valider" :
                    new OrderValidationThread(page).start();
                    break;
                case "Annuler la commande" :
                    new OrderCancellationThread(page).start();
                    break;
            }

        }

        if (page instanceof PackagingElfHomePage) {
            PackagingElfHomePage page = (PackagingElfHomePage) this.page;
            switch (e.getActionCommand()) {
                case "Précédent":
                    page.getCardLayoutForContentPanel().previous(page.getContentPanel());
                    break;
                case "Suivant":
                    page.getCardLayoutForContentPanel().next(page.getContentPanel());
                    break;
                case "Valider":
                    // Find the right order to validate by using the child's email
                    // because each card in contentPanel has a child's emails as name
                    String childEmail = new FindOrderByEmailThread(page).startThread();

                    // Search the right order to validate
                    Order order = null;
                    int i = 0; // iterator
                    do {
                        order = page.getPackagingElf().getOrdersManager().getValidOrders().get(i);
                        if (order.getOwner().getEmail().toString().equals(childEmail)) {
                            break;
                        }
                        order = null;
                        i++;
                    } while (i < page.getPackagingElf().getOrdersManager().getValidOrders().size());
                    if (order != null) {
                        page.getPackagingElf().getOrdersManager().setPrepared(order);
                        JOptionPane.showMessageDialog(page, "Commande prête pour la livraison.");
                        page.stopDisplayingOrder(order);
                    } else {
                        JOptionPane.showMessageDialog(page, "Un autre lutin a déjà validé cette commande.");
                        page.stopDisplayingOrder(order);
                    }
                    break;
            }
        }

    }

}
