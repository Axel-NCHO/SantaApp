import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.io.File;
import java.io.IOException;

/**
 * <h1>NewChildHomePage</h1>
 * Page on wich a {@link Child} can choose toys and send his/her order to Santa.
 * <hr>
 *
 * Split into 3 main panels :
 *
 * <ul><li>ToysPanel</li>
 * Implements a {@link GridLayout}.
 * It is the panel for choosing toys. This panel contains 3 sub-panels:
 * <ul><li>FirstToysRow</li>
 * It displays available toys that match the child's age.
 * <li>SecondToysRow</li>
 * It displays how many toys are selected and the maximal number of
 * toys allowed.
 * <li>ThirdToysRow</li>
 * It displays an image.</ul>
 *
 * <li>MessagePanel</li>
 * Containds a {@link UITextArea} that can be used by a {@link Child} to
 * write a message.
 *
 * <li>SendButtonPanel</li>
 * Contains the button used to send the {@link Order}.
 * </ul>
 *
 * After sending the order, the child is redirected to a new {@link ExistingChildHomePage}
 * where he/she can access a quick resume of the order.
 * <hr>*/
public class NewChildHomePage extends UIPage {

    /* Panel for toys list and scroll bar */
    private JPanel firstToysRow;

    /* Panel to display the number of toys chosen and the max number of toys per order */
    private JPanel secondToysRow;

    /* Panel for an image */
    private JPanel thirdToysRow;
    private DefaultListModel<Toy> listModel = new DefaultListModel<Toy>();
    private JList<Toy> listOfAvailableToys = new JList<Toy>(listModel); // The toys correspond to the age of the child
    private JScrollPane scrollPaneForToysList = new JScrollPane();
    private SynchronizedUITextField nbChosenToys = new SynchronizedUITextField(false); //
    private UITextField nbMaxToys = new UITextField();
    private UITextArea message = new UITextArea();
    private JButton sendButton = new JButton();
    private Child child; // We need to know the age of the child so that we can suggest toys of his/her age.
                         // We also need to know whose order it is.

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

    /**
     * Create the scroll panel for displaying the loaded toys.*/
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

    /**
     * Load the toys that correpond to the child's age from the database.*/
    private void fillListOfAvailableToys(){
        int minAge = 0, maxAge = 3;

        if (4 <= child.getAge() && child.getAge() <= 7){
            minAge = 4;maxAge = 7;
        }
        if (8 <= child.getAge() && child.getAge() <= 11){
            minAge = 8;maxAge = 11;
        }
        if (12 <= child.getAge() && child.getAge() <= 15){
            minAge = 12;maxAge = 15;
        }
        if (16 <= child.getAge() /*&& child.getAge() <= 18*/){
            minAge = 16;maxAge = 18;
        }
        String toysFolder = "AppDataBase/Toys.santaDB/" + Integer.toString(minAge) + "-" + Integer.toString(maxAge);
        String [] toysList = new File(toysFolder).list();
        if (toysList != null) {
            for (String toy : toysList){
                this.listModel.addElement((Toy)FileHelper.load(toysFolder +"/" + toy));
            }
        }
    }

    /**
     * Fields for displaying nbChosenToys and MAX_NB_TOYS_PER_ORDER.*/
    private void configSecondToysRow() {
        this.secondToysRow = new JPanel();
        this.secondToysRow.setBackground(UI_BG_COLOR);
        this.secondToysRow.setBorder(new EmptyBorder(150, 10, 150, 0));
        this.secondToysRow.setLayout(new BoxLayout(this.secondToysRow, BoxLayout.Y_AXIS));

        this.secondToysRow.add(new UILabel("Nombre de jouets choisis"));
        this.nbChosenToys.setBackground(UI_TEXTFIELD_COLOR);
        this.nbChosenToys.setBorder(new LineBorder(UI_BORDER_COLOR, 1, true));
        this.nbChosenToys.setEditable(false);
        this.secondToysRow.add(this.nbChosenToys);

        this.secondToysRow.add(new UILabel("Nombre maximal de jouets autorisé"));
        this.nbMaxToys.setBackground(UI_TEXTFIELD_COLOR);
        this.nbMaxToys.setBorder(new LineBorder(UI_BORDER_COLOR, 1, true));
        this.nbMaxToys.setText(Integer.toString(MAX_NB_TOYS_PER_ORDER));
        this.nbMaxToys.setEditable(false);
        this.secondToysRow.add(this.nbMaxToys);
    }

    /**
     * Field for an image.*/
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
        this.sendButton.setText("Envoyer ma commande au Père Noël");
        this.sendButton.addActionListener(new UIActionListener(this));
        sendButtonPanel.add(this.sendButton/*, BorderLayout.NORTH*/);
        return sendButtonPanel;
    }

    /**
     * @return Field that indicates the how many toys have been chosen.*/
    public SynchronizedUITextField getNbChosenToys() {
        return this.nbChosenToys;
    }

    /**
     * @return List that contains all the displayed toys.*/
    public JList<Toy> getListOfAvailableToys() {
        return this.listOfAvailableToys;
    }

    /**
     * @return {@link Child} owner of this page.*/
    public Child getChild() {
        return this.child;
    }

    /**
     * @return {@link UITextArea} field that contains the message.*/
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
