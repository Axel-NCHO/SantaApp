import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegistrationPage extends JFrame implements UiFinals {

    private JTextField lastName = new JTextField();
    private JTextField firstName = new JTextField();
    private JTextField email = new JTextField();
    private JTextField age = new JTextField();
    private JPasswordField pwd = new JPasswordField();
    private JTextField dateOfBirth = new JTextField();
    private JTextField city = new JTextField();
    private JTextField country = new JTextField();
    private JPanel nameEmailAndBirthPanel;
    private JPanel agePwdAndAddressPanel;
    private JButton submitButton = new JButton();

    public RegistrationPage(){
        this.setTitle(APP_NAME + " - v" + APP_VERSION + " - Création d'un nouveau compte");
        this.setSize(new Dimension(1200, 700));
        this.setResizable(false);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().add(createPersonalInformationsPanel());
        this.getContentPane().add(createSubmitButtonPanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JPanel createPersonalInformationsPanel(){
        JPanel personalInformationsPanel = new JPanel();
        personalInformationsPanel.setBackground(Color.WHITE);
        personalInformationsPanel.setLayout(new GridLayout(1, 2));
        configNameAndEmailPanel();
        personalInformationsPanel.add(this.nameEmailAndBirthPanel);
        configAgeAndPasswordPanel();
        personalInformationsPanel.add(this.agePwdAndAddressPanel);
        return personalInformationsPanel;

    }

    private void configNameAndEmailPanel(){
        this.nameEmailAndBirthPanel = new JPanel();
        this.nameEmailAndBirthPanel.setBorder(new EmptyBorder(150, 60, 130, 60));
        this.nameEmailAndBirthPanel.setBackground(Color.WHITE);
        this.nameEmailAndBirthPanel.setLayout(new BoxLayout(this.nameEmailAndBirthPanel, BoxLayout.Y_AXIS));
        this.nameEmailAndBirthPanel.add(new JLabel("Crée ton compte !"));
        this.nameEmailAndBirthPanel.add(new JLabel("Nom :"));
        this.nameEmailAndBirthPanel.add(this.firstName);
        this.nameEmailAndBirthPanel.add(new JLabel("Prénom :"));
        this.nameEmailAndBirthPanel.add(this.lastName);
        this.nameEmailAndBirthPanel.add(new JLabel("Adresse mail : "));
        this.nameEmailAndBirthPanel.add(this.email);
        this.nameEmailAndBirthPanel.add(new JLabel("Date de naissance : "));
        this.nameEmailAndBirthPanel.add(this.dateOfBirth);

    }


    private void configAgeAndPasswordPanel(){
        this.agePwdAndAddressPanel = new JPanel();
        this.agePwdAndAddressPanel.setBorder(new EmptyBorder(150, 60, 130, 60));
        this.agePwdAndAddressPanel.setBackground(Color.WHITE);
        this.agePwdAndAddressPanel.setLayout(new BoxLayout(this.agePwdAndAddressPanel, BoxLayout.Y_AXIS));
        this.agePwdAndAddressPanel.add(new JLabel("Crée ton compte !"));
        this.agePwdAndAddressPanel.add(new JLabel("Age :"));
        this.agePwdAndAddressPanel.add(this.age);
        this.agePwdAndAddressPanel.add(new JLabel("Ville :"));
        this.agePwdAndAddressPanel.add(this.city);
        this.agePwdAndAddressPanel.add(new JLabel("Pays :"));
        this.agePwdAndAddressPanel.add(this.country);
        this.agePwdAndAddressPanel.add(new JLabel("Nouveau mot de passe :"));
        this.agePwdAndAddressPanel.add(this.pwd);

    }

    private JPanel createSubmitButtonPanel(){
        JPanel submitButtonPanel = new JPanel();
        submitButtonPanel.setBorder(new EmptyBorder(10, 60, 10, 60));
        //submitButtonPanel.setBounds(0, 100, 100, 50);
        submitButtonPanel.setBackground(Color.WHITE);
        //submitButtonPanel.setLayout(new BorderLayout());
        this.submitButton.setText("Submit");
        submitButtonPanel.add(this.submitButton/*, BorderLayout.NORTH*/);
        return submitButtonPanel;

    }

    public void run(){
        this.setVisible(true);
    }

}
