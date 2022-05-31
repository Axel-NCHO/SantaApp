import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

public class RegistrationPage extends UIPage {

    private UITextField lastName = new UITextField();
    private UITextField firstName = new UITextField();
    private UITextField email = new UITextField();
    private UITextField age = new UITextField();
    private UITextField pwd = new UITextField();
    private UITextField dateOfBirth = new UITextField();
    private UITextField city = new UITextField();
    private UITextField country = new UITextField();
    private JPanel nameEmailAndBirthPanel;
    private JPanel agePwdAndAddressPanel;
    private JButton submitButton = new JButton();

    public RegistrationPage(){
        super("Crée ton compte !");
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().add(createPersonalInformationsPanel());
        this.getContentPane().add(createSubmitButtonPanel());
    }

    private JPanel createPersonalInformationsPanel(){
        JPanel personalInformationsPanel = new JPanel();
        personalInformationsPanel.setBackground(UI_BG_COLOR);
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
        this.nameEmailAndBirthPanel.setBackground(UI_BG_COLOR);
        this.nameEmailAndBirthPanel.setLayout(new BoxLayout(this.nameEmailAndBirthPanel, BoxLayout.Y_AXIS));
        this.nameEmailAndBirthPanel.add(new UILabel("Crée ton compte !"));
        this.nameEmailAndBirthPanel.add(new UILabel("Nom :"));
        this.nameEmailAndBirthPanel.add(this.firstName);
        this.nameEmailAndBirthPanel.add(new UILabel("Prénom :"));
        this.nameEmailAndBirthPanel.add(this.lastName);
        this.nameEmailAndBirthPanel.add(new UILabel("Adresse mail : "));
        this.nameEmailAndBirthPanel.add(this.email);
        this.nameEmailAndBirthPanel.add(new UILabel("Age : "));
        this.dateOfBirth.setText("Ex : 6");
        this.nameEmailAndBirthPanel.add(this.dateOfBirth);

    }


    private void configAgeAndPasswordPanel(){
        this.agePwdAndAddressPanel = new JPanel();
        this.agePwdAndAddressPanel.setBorder(new EmptyBorder(150, 60, 130, 60));
        this.agePwdAndAddressPanel.setBackground(UI_BG_COLOR);
        this.agePwdAndAddressPanel.setLayout(new BoxLayout(this.agePwdAndAddressPanel, BoxLayout.Y_AXIS));
        this.agePwdAndAddressPanel.add(new UILabel("    "));
        this.agePwdAndAddressPanel.add(new UILabel("Rue :"));
        this.agePwdAndAddressPanel.add(this.age);
        this.agePwdAndAddressPanel.add(new UILabel("Ville :"));
        this.agePwdAndAddressPanel.add(this.city);
        this.agePwdAndAddressPanel.add(new UILabel("Pays :"));
        this.agePwdAndAddressPanel.add(this.country);
        this.agePwdAndAddressPanel.add(new UILabel("Nouveau mot de passe :"));
        this.agePwdAndAddressPanel.add(this.pwd);

    }

    private JPanel createSubmitButtonPanel(){
        JPanel submitButtonPanel = new JPanel();
        submitButtonPanel.setBorder(new EmptyBorder(10, 60, 10, 60));
        //submitButtonPanel.setBounds(0, 100, 100, 50);
        submitButtonPanel.setBackground(UI_BG_COLOR);
        //submitButtonPanel.setLayout(new BorderLayout());
        this.submitButton.setText("Submit");
        submitButton.setBackground(UI_BG_COLOR_2);
        submitButton.setForeground(UI_TEXT_COLOR);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        this.submitButton.addActionListener(new UIActionListener(this));
        submitButtonPanel.add(this.submitButton/*, BorderLayout.NORTH*/);
        return submitButtonPanel;

    }

    public UITextField getFirstName() {
        return this.firstName;
    }

    public UITextField getLastName() {
        return this.lastName;
    }

    public UITextField getEmail(){
        return this.email;
    }

    public UITextField getPassword(){
        return this.pwd;
    }

    public UITextField getAge(){
        return this.dateOfBirth;
    }

    public String getAddress(){
        return age.getText() + " " + city.getText() + ", " + country.getText();
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
