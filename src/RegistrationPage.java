import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * <h1>Registration Page</h1>
 * Page used by a {@link Child} to register.
 * <hr>
 * Splitted into 2 main panels :
 *
 * <ul><li>personnalInfosPanel</li>
 * For all the personal informations (name, age, ...). It implements a {@link GridLayout}.
 *
 * <li>submitButtonPanel</li>
 * For the submit button.</ul>
 *
 * If all the required fields are filled and valid, the child is redirected to a
 * {@link NewChildHomePage} to compose his/her order.
 * <hr>*/
public class RegistrationPage extends UIPage {

    /* Field for last name */
    private UITextField lastName = new UITextField();

    /* Field for first name */
    private UITextField firstName = new UITextField();

    /* Field for e-mail */
    private UITextField email = new UITextField();

    /* Field for neighborhood */
    private UITextField age = new UITextField();

    /* Field for password */
    private UITextField pwd = new UITextField();

    /* Field for age */
    private UITextField dateOfBirth = new UITextField();

    /* Field for city */
    private UITextField city = new UITextField();

    /* Field for country */
    private UITextField country = new UITextField();
    private JPanel nameEmailAndBirthPanel;
    private JPanel agePwdAndAddressPanel;
    private JButton submitButton = new JButton();
    private JButton connectionButton = new JButton();


    public RegistrationPage(){
        super("Crée ton compte !");
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().add(createPersonalInformationsPanel());
        this.getContentPane().add(createButtonsPanel());
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
        this.nameEmailAndBirthPanel.add(new UILabel("Nom * :"));
        this.nameEmailAndBirthPanel.add(this.firstName);
        this.nameEmailAndBirthPanel.add(new UILabel("Prénom * :"));
        this.nameEmailAndBirthPanel.add(this.lastName);
        this.nameEmailAndBirthPanel.add(new UILabel("Adresse mail * : "));
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
        this.agePwdAndAddressPanel.add(new UILabel("Nouveau mot de passe * :"));
        this.agePwdAndAddressPanel.add(this.pwd);

    }

    private JPanel createButtonsPanel(){
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(10, 60, 10, 60));
        //submitButtonPanel.setBounds(0, 100, 100, 50);
        buttonsPanel.setBackground(UI_BG_COLOR);
        //submitButtonPanel.setLayout(new BorderLayout());

        this.submitButton.setText("Soumettre");
        submitButton.setBackground(UI_BG_COLOR_2);
        submitButton.setForeground(UI_TEXT_COLOR);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        this.submitButton.addActionListener(new UIActionListener(this));
        buttonsPanel.add(submitButton);

        buttonsPanel.add(new UILabel("Tu as déjà un compte ? >>>>>"));

        this.connectionButton.setText("Je me connecte !");
        connectionButton.setBackground(UI_BG_COLOR_2);
        connectionButton.setForeground(UI_TEXT_COLOR);
        connectionButton.setOpaque(true);
        connectionButton.setBorderPainted(false);
        this.connectionButton.addActionListener(new UIActionListener(this));
        buttonsPanel.add(this.connectionButton);

        return buttonsPanel;

    }
    /**
     * @return Field that contains a child's first name.*/
    public UITextField getFirstName() {
        return this.firstName;
    }

    /**
     * @return Field that contains a child's last name.*/
    public UITextField getLastName() {
        return this.lastName;
    }

    /**
     * @return Field that contains a child's e-mail.*/
    public UITextField getEmail(){
        return this.email;
    }

    /**
     * @return Field that contains a child's password.*/
    public UITextField getPassword(){
        return this.pwd;
    }

    /**
     * @return Field that contains a child's age.*/
    public UITextField getAge(){
        return this.dateOfBirth;
    }

    /**
     * @return A child's address. It is the concatenation of the fields
     * neighborhood, city and country.*/
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
