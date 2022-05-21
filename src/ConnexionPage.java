import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConnexionPage extends JFrame implements UiFinals {

    private JTextField email = new JTextField();
    private JPasswordField pwd = new JPasswordField();
    private JButton connexionButton = new JButton();
    private JButton registrationButton = new JButton();
    private JPanel emailPanel;
    private JPanel pwdPanel;

    public ConnexionPage(){
        this.setTitle(APP_NAME + " - v" + APP_VERSION + " - Connexion");
        this.setSize(new Dimension(1200, 700));
        this.setResizable(false);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().add(createPersonalInformationsPanel());
        this.getContentPane().add(createButtonsPanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JPanel createPersonalInformationsPanel(){
        JPanel personalInformationsPanel = new JPanel();
        personalInformationsPanel.setBackground(Color.WHITE);
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
        this.emailPanel.setBackground(Color.WHITE);
        this.emailPanel.setLayout(new BoxLayout(this.emailPanel, BoxLayout.Y_AXIS));
        this.emailPanel.add(new JLabel("Connectes-toi à ton compte !"));
        this.emailPanel.add(new JLabel("Email :"));
        this.emailPanel.add(this.email);

    }

    private void configPasswordPanel(){
        this.pwdPanel = new JPanel();
        this.pwdPanel.setBorder(new EmptyBorder(200, 60, 250, 60));
        this.pwdPanel.setBackground(Color.WHITE);
        this.pwdPanel.setLayout(new BoxLayout(this.pwdPanel, BoxLayout.Y_AXIS));
        this.pwdPanel.add(new JLabel("Connectes-toi à ton compte !"));
        this.pwdPanel.add(new JLabel("Mot de passe :"));
        this.pwdPanel.add(this.pwd);

    }

    private JPanel createButtonsPanel(){
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(10, 60, 80, 60));
        //submitButtonPanel.setBounds(0, 100, 100, 50);
        buttonsPanel.setBackground(Color.WHITE);
        //buttonsPanel.setLayout(new BorderLayout());
        this.connexionButton.setText("Je me connecte !");
        this.registrationButton.setText("Je crée un compte !");
        buttonsPanel.add(this.connexionButton);
        buttonsPanel.add(new JLabel("     Nouveau ici ? >>>>>>> "));
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

    public void run(){
        this.setVisible(true);
    }

}