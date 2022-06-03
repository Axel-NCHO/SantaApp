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

    private UIPage page;

    public UIActionListener(UIPage page){
        this.page = page;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /* Action performed if the calling component is on a registration page */
        if (this.page instanceof RegistrationPage){
            AccountCreationThread thread = new AccountCreationThread((RegistrationPage) this.page);
            Child child = thread.startThread();
            if(child != null) {
                new NewChildThread(child).start();
                new CloseWindowThread(page).start();
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
            // On veut pouvoir reafficher NewChildHomePage mais contenant déjà la commande de l'enfant
            // ExistingChildHomePage page = (ExistingChildHomePage) this.page;
            // ...
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
                    // Find the right order to validate or cancel by using the child's email
                    // because the cards in contentPanel have child's emails as name
                    String childEmail = new FindOrderByEmailThread(page).startThread();

                    // remove toys not selected by santa
                    for (Order order : page.getOrdersManager().getReceivedOrders()) {
                        if (order.getOwner().getEmail().toString().equals(childEmail)) {
                            for (JCheckBox checkBox : page.getValidatedToys()) {
                                if (!checkBox.isSelected()) {
                                    page.getOrdersManager().removeToy(order, new Toy(checkBox.getText()));
                                }
                            }
                            if (order.getToys().size() != 0) {
                                // move order file to valid orders repo
                                page.getOrdersManager().setValid(order);
                                JOptionPane.showMessageDialog(page, "Commande validée.");
                            } else {
                                JOptionPane.showMessageDialog(page, "Il semble que vous n'ayez sélectionné aucun jouet.\n"+
                                        "Si ce n'est pas une erreur, veuillez utiliser le bouton\n"+
                                        "'Annuler la commnde' pour annuler la commande","Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }/*
                    JPanel panelToErase = new JPanel();
                    panelToErase.setName(childEmail);
                    page.getCardLayoutForContentPanel().removeLayoutComponent(panelToErase);*/
                    break;
                case "Annuler la commande" :
                    break;
            }

        }

    }

}
