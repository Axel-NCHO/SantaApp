import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ConnexionPage extends UIPage {

    private UITextField email = new UITextField();
    private UITextField pwd = new UITextField();
    private JButton connexionButton = new JButton();
    private JButton registrationButton = new JButton();
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
        //submitButtonPanel.setBounds(0, 100, 100, 50);
        buttonsPanel.setBackground(UI_BG_COLOR);
        //buttonsPanel.setLayout(new BorderLayout());
        this.connexionButton.setText("Je me connecte !");
        this.connexionButton.addActionListener(new UIActionListener(this));
        this.registrationButton.setText("Je crée un compte !");
        this.registrationButton.addActionListener(new UIActionListener(this));
        buttonsPanel.add(this.connexionButton);
        buttonsPanel.add(new UILabel("     Nouveau ici ? >>>>>>> "));
        buttonsPanel.add(this.registrationButton);
        return buttonsPanel;
        /*
        buttonsPanel.setLayout(new BorderLayout());
        this.connexionButton.setText("Je me connecte !");
        this.registrationButton.setText("Je crée un compte !");
        buttonsPanel.add(this.connexionButton, BorderLayout.NORTH);
        buttonsPanel.add(new JLabel(">"), BorderLayout.WEST);
        buttonsPanel.add(new JLabel("------- Nouveau ici ? ---------"), BorderLayout.CENTER);
        buttonsPanel.add(new JLabel("<"), BorderLayout.EAST);
        buttonsPanel.add(this.registrationButton, BorderLayout.SOUTH);
        return buttonsPanel;*/
        /*
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        this.connexionButton.setText("Je me connecte !");
        this.registrationButton.setText("Je crée un compte !");
        buttonsPanel.add(this.connexionButton);
        buttonsPanel.add(new JLabel("------- Nouveau ici ? ---------"));
        buttonsPanel.add(this.registrationButton);
        return buttonsPanel;*/
    }

    public UITextField getEmail() {
        return this.email;
    }

    public UITextField getPwd() {
        return pwd;
    }

    public JButton getConnexionButton() {
        return connexionButton;
    }

    public JButton getRegistrationButton() {
        return registrationButton;
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