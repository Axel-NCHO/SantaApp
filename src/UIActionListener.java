import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIActionListener implements ActionListener {

    private UIPage page;

    public UIActionListener(UIPage page){
        this.page = page;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.page instanceof RegistrationPage){
            AccountCreationThread thread = new AccountCreationThread((RegistrationPage) this.page);
            Child child = thread.startThread();
            if(child != null) {
                new NewChildThread(child).start();
                //this.page.dispatchEvent(new WindowEvent(this.page, WindowEvent.WINDOW_CLOSING));
                new CloseWindowThread(page).start();
            }
        }

        if (this.page instanceof NewChildHomePage) {
            OrderSubmissionThread thread = new OrderSubmissionThread((NewChildHomePage) this.page);
            Order order = thread.startThread();
            if(order != null){
                new OrderCheckingThread(order).start();
                new CloseWindowThread(page).start();
            }
        }

        if (this.page instanceof ConnexionPage) {
            if (e.getActionCommand() == "Je crée un compte !") { // The user wants to create an account
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

        if (page instanceof ExistingChildHomePage) {
            // On veut pouvoir reafficher NewChildHomePage mais contenant déjà la commande de l'enfant
            // ExistingChildHomePage page = (ExistingChildHomePage) this.page;
            // ...
        }

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
            }

        }

    }

}
