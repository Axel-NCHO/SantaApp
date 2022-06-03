import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * <h1>ConnexionPage</h1>
 * Page used by a {@link User} to connect to his/her account.
 * <hr>
 *
 * Splitted into 2 main panels :
 *
 * <ul><li>personnalInfosPanel</li>
 * For all the personal informations (e-mail and password). It implements a {@link GridLayout}.
 *
 * <li>buttonsPanel</li>
 * For the 2 buttons (submit and register)</ul>
 *
 * If connection is done with success, the user is redirected to the right
 * page depending on the e-mail:
 * <ul><li>{@link SantaHomePage} if it matches Santa.</li>
 * <li>Packaging or Medical Elf home page if it matches an {@link Elf}.</li>
 * <li>{@link ExistingChildHomePage} by default.</li></ul>
 * <hr>*/
public class ConnexionPage extends UIPage {

    /* The e-mail field of the connection page */
    private final UITextField email = new UITextField();

    /* The password field of the connection page */
    private final UITextField pwd = new UITextField();

    /* The connection button */
    private final JButton connexionButton = new JButton();

    /* The button to register if the account does not exist */
    private final JButton registrationButton = new JButton();
    private JPanel emailPanel;
    private JPanel pwdPanel;

    public ConnexionPage(){
        super("Connexion");
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().add(createPersonalInformationsPanel());
        this.getContentPane().add(createButtonsPanel());
    }

    private JPanel createPersonalInformationsPanel(){
        JPanel personalInformationsPanel = new JPanel();
        personalInformationsPanel.setBackground(UI_BG_COLOR);
        personalInformationsPanel.setLayout(new GridLayout(1, 2));
        configEmailPanel();
        personalInformationsPanel.add(this.emailPanel);
        configPasswordPanel();
        personalInformationsPanel.add(this.pwdPanel);

        return personalInformationsPanel;
    }

    private void configEmailPanel(){
        this.emailPanel = new JPanel();
        this.emailPanel.setBorder(new EmptyBorder(200, 60, 250, 60));
        this.emailPanel.setBackground(UI_BG_COLOR);
        this.emailPanel.setLayout(new BoxLayout(this.emailPanel, BoxLayout.Y_AXIS));
        this.emailPanel.add(new UILabel("Connectes-toi à ton compte !"));
        this.emailPanel.add(new UILabel("Email :"));
        this.emailPanel.add(this.email);
    }

    private void configPasswordPanel(){
        this.pwdPanel = new JPanel();
        this.pwdPanel.setBorder(new EmptyBorder(200, 60, 250, 60));
        this.pwdPanel.setBackground(UI_BG_COLOR);
        this.pwdPanel.setLayout(new BoxLayout(this.pwdPanel, BoxLayout.Y_AXIS));
        this.pwdPanel.add(new UILabel("      "));
        this.pwdPanel.add(new UILabel("Mot de passe :"));
        this.pwdPanel.add(this.pwd);
    }

    private JPanel createButtonsPanel(){
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(10, 60, 80, 60));
        buttonsPanel.setBackground(UI_BG_COLOR);
        this.connexionButton.setText("Je me connecte !");
        this.connexionButton.addActionListener(new UIActionListener(this));
        this.registrationButton.setText("Je crée un compte !");
        this.registrationButton.addActionListener(new UIActionListener(this));
        buttonsPanel.add(this.connexionButton);
        buttonsPanel.add(new UILabel("     Nouveau ici ? >>>>>>> "));
        buttonsPanel.add(this.registrationButton);

        return buttonsPanel;
    }

    /**
     * @return {@link UITextField} that contains the e-mail.*/
    public UITextField getEmail() {
        return this.email;
    }

    /**
     * @return {@link UITextField} that contains the password.*/
    public UITextField getPwd() {
        return pwd;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}