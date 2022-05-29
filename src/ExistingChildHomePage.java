import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ExistingChildHomePage extends UIPage {

    private Child owner;
    private JPanel orderContentPanel;
    private JPanel statusPanel;
    private DefaultListModel<Toy> listModel = new DefaultListModel<Toy>();
    private JList<Toy> wishList = new JList<Toy>(this.listModel);
    private UITextArea message = new UITextArea();
    private JPanel checkBoxesPanel;
    private JCheckBox sentCheckBox = new JCheckBox("Envoyée au Père Noël");
    private JCheckBox validatedCheckBox = new JCheckBox("Validée par le Père Noël");
    private JCheckBox readyCheckBox = new JCheckBox("Prête à être livrée");
    private JPanel modifyButtonPanel;
    private JButton modifyButton = new JButton("Modifier la commande ?");


    public ExistingChildHomePage(Child owner){
        super("Consultes ta commnde " + owner.getEmail().toString() + " !");
        this.owner = owner;
        this.getContentPane().setLayout(new GridLayout(1, 2));
        this.getContentPane().add(createOrderPanel());
        this.getContentPane().add(createOrderStatusPanel());
    }

    private JPanel createOrderPanel() {
        JPanel orderPanel = new JPanel();
        orderPanel.setBackground(UI_BG_COLOR);
        orderPanel.setBorder(new EmptyBorder(10, 10, 300, 10));
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderPanel.add(new UILabel("Contenu actuel de ta commande"));
        configOrderContentPanel();
        orderPanel.add(this.orderContentPanel);
        return orderPanel;
    }

    private void configOrderContentPanel(){
        this.orderContentPanel = new JPanel();
        this.orderContentPanel.setBackground(UI_BG_COLOR);
        this.orderContentPanel.setBorder(new EmptyBorder(100, 10, 0, 10));
        this.orderContentPanel.setLayout(new BoxLayout(this.orderContentPanel, BoxLayout.Y_AXIS));
        this.wishList.setBackground(UI_BG_COLOR);
        this.wishList.setForeground(UI_TEXT_COLOR);
        this.orderContentPanel.add(this.wishList);
        this.message.setEditable(false);
        this.message.setBackground(UI_BG_COLOR);
        this.message.setForeground(UI_TEXT_COLOR);
        this.orderContentPanel.add(this.message);
    }

    private JPanel createOrderStatusPanel() {
        JPanel orderStatusPanel = new JPanel();
        orderStatusPanel.setBackground(UI_BG_COLOR_2);
        orderStatusPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        orderStatusPanel.setLayout(new BoxLayout(orderStatusPanel, BoxLayout.Y_AXIS));
        orderStatusPanel.add(new UILabel("Statut de la commande"));
        configStatusPanel();
        orderStatusPanel.add(this.statusPanel);

        return orderStatusPanel;
    }

    public void configStatusPanel(){
        this.statusPanel = new JPanel();
        this.statusPanel.setBackground(UI_BG_COLOR_2);
        this.statusPanel.setBorder(new EmptyBorder(200, 10, 0, 10));
        this.statusPanel.setLayout(new BoxLayout(this.statusPanel, BoxLayout.Y_AXIS));
        configCheckBoxesPanel();
        this.statusPanel.add(this.checkBoxesPanel);
        configModifyButtonPanel();
        this.statusPanel.add(this.modifyButtonPanel);
    }

    private void configCheckBoxesPanel() {
        this.checkBoxesPanel = new JPanel();
        this.checkBoxesPanel.setBackground(UI_BG_COLOR_2);
        this.checkBoxesPanel.setBorder(new EmptyBorder(0, 0, 100, 0));
        this.checkBoxesPanel.setLayout(new BoxLayout(this.checkBoxesPanel, BoxLayout.Y_AXIS));
        this.sentCheckBox.setBackground(UI_BG_COLOR_2);
        this.sentCheckBox.setForeground(UI_TEXT_COLOR);
        this.validatedCheckBox.setBackground(UI_BG_COLOR_2);
        this.validatedCheckBox.setForeground(UI_TEXT_COLOR);
        this.readyCheckBox.setBackground(UI_BG_COLOR_2);
        this.readyCheckBox.setForeground(UI_TEXT_COLOR);
        this.checkBoxesPanel.add(this.sentCheckBox);
        this.checkBoxesPanel.add(this.validatedCheckBox);
        this.checkBoxesPanel.add(this.readyCheckBox);
    }

    private void configModifyButtonPanel() {
        this.modifyButtonPanel = new JPanel();
        this.modifyButtonPanel.setBackground(UI_BG_COLOR_2);
        //this.modifyButtonPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
        /* Add in keyPressed listener | this.modifyButton.setBackground(UI_BG_COLOR);
        this.modifyButton.setForeground(UI_FG_COLOR);
        this.modifyButton.setOpaque(true);
        this.modifyButton.setBorderPainted(false);*/
        this.modifyButtonPanel.add(this.modifyButton);
    }

    public void setWishList(ArrayList<Toy> toys) {
        if (toys != null){
            for (Toy toy : toys){
                this.listModel.addElement(toy);
            }
        }
    }

    public void setMessage(String message) {
        if (message != null){
            String messageBoxContent = "Message envoyé au Père Noël :\n";
            messageBoxContent += message;
            this.message.setText(messageBoxContent);
        } else {
            this.message.setText("Tu n'as pas envoyé de message au Père Noël.");
        }
    }

    public void selectSentCheckBox(){
        this.sentCheckBox.setSelected(true);
        this.validatedCheckBox.setSelected(false);
        this.readyCheckBox.setSelected(false);
    }

    public void selectValidatedCheckBox(){
        this.validatedCheckBox.setSelected(true);
        this.sentCheckBox.setSelected(false);
        this.readyCheckBox.setSelected(false);
    }

    public void selectReadyCheckBox(){
        this.readyCheckBox.setSelected(true);
        this.sentCheckBox.setSelected(false);
        this.validatedCheckBox.setSelected(false);
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
