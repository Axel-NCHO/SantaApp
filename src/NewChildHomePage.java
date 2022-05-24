import javax.swing.*;
import java.awt.*;
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
    private UITextField nbChoosenToys = new UITextField();
    private UITextField nbMaxToys = new UITextField();
    private UITextArea message = new UITextArea();
    private JButton sendButton = new JButton();

    public NewChildHomePage(){
        super("Commande tes cadeaux");
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
        //fillListOfAvailableToys(); cad fill listModel
        for(int i=0; i<50; i++){
            this.listModel.addElement(new Toy("PS5", 13));
        }
        this.scrollPaneForToysList.setBorder(new LineBorder(UI_BORDER_COLOR, 1, true));
        this.scrollPaneForToysList.setViewportView(this.listOfAvailableToys);
        this.listOfAvailableToys.setLayoutOrientation(JList.VERTICAL);
        this.firstToysRow.add(this.scrollPaneForToysList);
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
        sendButtonPanel.add(this.sendButton/*, BorderLayout.NORTH*/);
        return sendButtonPanel;
    }

}
