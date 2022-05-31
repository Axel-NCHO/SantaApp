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
                //new OrderCheckingThread(order).start();
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

        if (this.page instanceof SantaHomePage) {
            SantaHomePage page = (SantaHomePage) this.page;
            switch (e.getActionCommand()) {
                case "Commandes en attente de validation" :
                    page.clear();
                    page.load(page.getOngoingOrdersPage());
                    break;
                case "Lutins préparateurs" :
                    page.clear();
                    page.load(page.getPackagingElfPage());
                    break;
                case "Lutins soigneurs" :
                    page.clear();
                    page.load(page.getHealingElfPage());
                    break;
            }

        }

    }
}
