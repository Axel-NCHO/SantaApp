import javax.swing.*;

public class WelcomeThread extends Thread {

    private final UIPage page;

    public WelcomeThread(UIPage page) {
        this.page = page;
    }

    @Override
    public void run() {
        if (page instanceof NewChildHomePage) {
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

        if (page instanceof SantaHomePage) {
            String tutorial = """
                    Bienvenue Père Noël.
                    Ici, tu peux valider ou annuler les commandes des enfants.
                    Le nombre de jouets dépend de la couleur dans laquelle est affichée
                    le nom de l'enfant :
                        ->rouge  : 0 jouets, il faut annuler la commande
                        ->jaune  : 1 seul jouet
                        ->orange : 2 joeuts
                        ->vert   : tous les jouets""";
            JOptionPane.showMessageDialog(page, tutorial, "Tutoriel", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
