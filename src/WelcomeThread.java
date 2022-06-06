import javax.swing.*;

public class WelcomeThread extends Thread {

    private final NewChildHomePage page;

    public WelcomeThread(NewChildHomePage page) {
        this.page = page;
    }

    @Override
    public void run() {
        String welcomeMessage = """
                Bienvenue sur l'appli du Père Noël !
                Ici, tu peux choisir jusqu'à 5 jouets et laisser un message au Père Noël.
                Si tu as été sage, le Père Noël passera à Noël.
                Pour choisir plusieurs jouets, fait Ctrl+Click.
                Utilise le bouton 'Envoyer' pour envoyer. 
                Tu peux te reconnecter plutard pour suivre l'évolution de ta commande.
                Salut !
                """;
        JOptionPane.showMessageDialog(page, welcomeMessage, "Salut !", JOptionPane.INFORMATION_MESSAGE);
    }
}
