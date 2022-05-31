import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.io.File;
import java.io.IOException;

public class NewChildHomePage extends UIPage {

    private JPanel firstToysRow;
    private JPanel secondToysRow;
    private JPanel thirdToysRow;
    private DefaultListModel<Toy> listModel = new DefaultListModel<Toy>();
    private JList<Toy> listOfAvailableToys = new JList<Toy>(listModel); // Corresponding to the age of the child
    private JScrollPane scrollPaneForToysList = new JScrollPane();
    private SynchronizedUITextField nbChoosenToys = new SynchronizedUITextField(false); //
    private UITextField nbMaxToys = new UITextField();
    private UITextArea message = new UITextArea();
    private JButton sendButton = new JButton();
    private Child child; // We need to know the age of the child so that we can suggest toys of his/her age.
                         // We also need to know whose order ii is.

    public NewChildHomePage(Child child){
        super("Commande tes cadeaux " + child.getLastName());
        this.child = child;
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().add(createToysPanel());
        this.getContentPane().add(createMessagePanel());
        this.getContentPane().add(createSendButtonPanel());
    }

    private JPanel createToysPanel() {
        JPanel toysPanel = new JPanel();
        toysPanel.setBackground(UI_BG_COLOR);
        toysPanel.setLayout(new GridLayout(1, 3));
        configFirstToysRow();
        toysPanel.add(this.firstToysRow);
        configSecondToysRow();
        toysPanel.add(this.secondToysRow);
        configThirdToysRow();
        toysPanel.add(this.thirdToysRow);
        return toysPanel;
    }

    private void configFirstToysRow() {
        this.firstToysRow = new JPanel();
        this.firstToysRow.setBackground(UI_BG_COLOR);
        this.firstToysRow.setBorder(new EmptyBorder(10, 20, 0, 10));
        this.firstToysRow.setLayout(new BoxLayout(this.firstToysRow, BoxLayout.Y_AXIS));
        this.firstToysRow.add(new UILabel("Choisis " + MAX_NB_TOYS_PER_ORDER + " jouets. Ils sont tous de ton âge !"));
        fillListOfAvailableToys();
        this.scrollPaneForToysList.setBorder(new LineBorder(UI_BORDER_COLOR, 1, true));
        this.scrollPaneForToysList.setViewportView(this.listOfAvailableToys);
        this.listOfAvailableToys.setLayoutOrientation(JList.VERTICAL);
        this.firstToysRow.add(this.scrollPaneForToysList);
    }

    private void fillListOfAvailableToys(){
        Integer minAge = 0, maxAge = 3;

        if (4 <= child.getAge() && child.getAge() <= 7){
            minAge = 4;maxAge = 7;
        }
        if (8 <= child.getAge() && child.getAge() <= 11){
            minAge = 8;maxAge = 11;
        }
        if (12 <= child.getAge() && child.getAge() <= 15){
            minAge = 12;maxAge = 15;
        }
        if (16 <= child.getAge() && child.getAge() <= 18){
            minAge = 16;maxAge = 18;
        }
        String toysFolder = "AppDataBase/Toys.santaDB/" + minAge.toString() + "-" + maxAge.toString();
        String [] toysList = new File(toysFolder).list();
        for (String toy : toysList){
            this.listModel.addElement((Toy)FileHelper.load(toysFolder +"/" + toy));
        }
    }

    private void configSecondToysRow() {
        this.secondToysRow = new JPanel();
        this.secondToysRow.setBackground(UI_BG_COLOR);
        this.secondToysRow.setBorder(new EmptyBorder(150, 10, 150, 0));
        this.secondToysRow.setLayout(new BoxLayout(this.secondToysRow, BoxLayout.Y_AXIS));
        this.secondToysRow.add(new UILabel("Nombre de jouets choisis"));
        this.nbChoosenToys.setBackground(UI_TEXTFIELD_COLOR);
        this.nbChoosenToys.setBorder(new LineBorder(UI_BORDER_COLOR, 1, true));
        this.nbChoosenToys.setEditable(false);
        /*try {
            this.nbChoosenToys.setText(Integer.toString(this.listOfAvailableToys.getSelectedIndices().length));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        this.secondToysRow.add(this.nbChoosenToys);
        this.secondToysRow.add(new UILabel("Nombre maximal de jouets autorisé"));
        this.nbMaxToys.setBackground(UI_TEXTFIELD_COLOR);
        this.nbMaxToys.setBorder(new LineBorder(UI_BORDER_COLOR, 1, true));
        this.nbMaxToys.setText(Integer.toString(MAX_NB_TOYS_PER_ORDER));
        this.nbMaxToys.setEditable(false);
        this.secondToysRow.add(this.nbMaxToys);
    }

    private void configThirdToysRow() {
        this.thirdToysRow = new JPanel();
        this.thirdToysRow.setBackground(UI_BG_COLOR);
        this.thirdToysRow.setLayout(new BorderLayout());
        try{
            BufferedImage image = ImageIO.read(new File("img/gift.png"));
            UILabel imageLabel = new UILabel(new ImageIcon(image));
            this.thirdToysRow.add(imageLabel, BorderLayout.CENTER);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private JPanel createMessagePanel() {
        JPanel messagePanel = new JPanel();
        messagePanel.setBorder(new EmptyBorder(20, 20, 0, 20));
        messagePanel.setBackground(UI_BG_COLOR);
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        this.message.setText("Ecris un message au Père Noël ...");
        this.message.setBorder(new LineBorder(UI_BORDER_COLOR, 1, true));
        messagePanel.add(this.message);
        return messagePanel;
    }

    private JPanel createSendButtonPanel() {
        JPanel sendButtonPanel = new JPanel();
        sendButtonPanel.setBackground(UI_BG_COLOR);
        sendButtonPanel.setBorder(new EmptyBorder(40, 10, 50, 10));
        //sendButtonPanel.setLayout(new BorderLayout());
        this.sendButton.setText("Envoyer ma commande au Père Noël");
        this.sendButton.addActionListener(new UIActionListener(this));
        sendButtonPanel.add(this.sendButton/*, BorderLayout.NORTH*/);
        return sendButtonPanel;
    }

    public SynchronizedUITextField getNbChoosenToys() {
        return this.nbChoosenToys;
    }

    public JList getListOfAvailableToys() {
        return this.listOfAvailableToys;
    }


    public Child getChild() {
        return this.child;
    }

    public UITextArea getMessage() {
        return this.message;
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
