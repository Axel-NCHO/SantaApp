import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * <h1>PackagingElfHomePage</h1>
 * Page used by packaging elfs to set orders prepared.
 * They have access to the toys validated by Santa and cannot modify them.*/
public class PackagingElfHomePage extends UIPage {

    /* Packaging elf owner of this page loaded from users database */
    private final PackagingElf packagingElf;

    /* List model to display validated orders */
    private DefaultListModel<Order> listModel;

    /* Display one order at a time */
    private JPanel contentPanel;

    private final CardLayout cardLayoutForContentPanel = new CardLayout();

    private final ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();

    public PackagingElfHomePage(PackagingElf packagingElf) {
        super("Page d'accueil Lutin préparateur");
        this.packagingElf = packagingElf;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(createValidOrdersPanel());
    }

    private JPanel createValidOrdersPanel() {
        JPanel validOrdersPanel = new JPanel();
        validOrdersPanel.setBackground(Color.BLUE);
        validOrdersPanel.setLayout(new GridLayout(1, 2));
        validOrdersPanel.add(createListPanel(packagingElf.getOrdersManager().getValidOrders()));
        validOrdersPanel.add(createContentPanel(packagingElf.getOrdersManager().getValidOrders()));
        return validOrdersPanel;
    }

    /**
     * Create a panel that displays the elements in one list of an {@link OrdersManager}.
     * @param managersList the list to be displayed.
     */
    private JPanel createListPanel(ArrayList<Order> managersList) {
        JPanel listPanel = new JPanel();
        listPanel.setBackground(UI_BG_COLOR_2);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.add(new UILabel("Commandes validées"));

        listModel = new DefaultListModel<Order>();
        /* List of received orders */
        JList<Order> list = new JList<Order>(listModel);
        list.setCellRenderer(new UIListCellRenderer());
        for (Order order : managersList) {
            listModel.addElement(order);
        }
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new LineBorder(UI_BORDER_COLOR, 1, true));
        list.setLayoutOrientation(JList.VERTICAL);
        list.setEnabled(false);
        list.setBackground(UI_BG_COLOR_2);
        list.setForeground(UI_TEXT_COLOR);
        scrollPane.setViewportView(list);
        listPanel.add(scrollPane);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(UI_BG_COLOR_2);
        buttonPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
        JButton previous = new JButton("Précédent");
        previous.addActionListener(new UIActionListener(this));
        JButton validateButton = new JButton("Valider");
        validateButton.addActionListener(new UIActionListener(this));
        JButton next = new JButton("Suivant");
        next.addActionListener(new UIActionListener(this));
        buttonPanel.add(previous);
        buttonPanel.add(validateButton);
        buttonPanel.add(next);
        listPanel.add(buttonPanel);

        return listPanel;
    }

    public JPanel createContentPanel(ArrayList<Order> managersList) {
        contentPanel = new JPanel();
        contentPanel.setLayout(cardLayoutForContentPanel);
        contentPanel.setBackground(UI_BG_COLOR);

        for (Order order : managersList) {
            contentPanel.add(order.getOwner().getEmail().toString(), create_InContentPanel(order));
        }

        return contentPanel;
    }

    /**
     * _InContentPanel is the card that displays the content of an {@link Order}.
     * The parent component of _inContentPanel is contentPanel (implements {@link CardLayout}).*/
    private JPanel create_InContentPanel(Order order) {
        JPanel _inContentPanel = new JPanel();
        _inContentPanel.setBackground(UI_BG_COLOR);
        _inContentPanel.setName(order.getOwner().getEmail().toString());
        _inContentPanel.setLayout(new BoxLayout(_inContentPanel, BoxLayout.Y_AXIS));

        JPanel personalInfosPanel = new JPanel();
        personalInfosPanel.setBackground(UI_BG_COLOR);
        personalInfosPanel.add(new UILabel(order.getOwner().toString()));
        _inContentPanel.add(personalInfosPanel);

        JPanel whishListPanel = new JPanel();
        whishListPanel.setBorder(new EmptyBorder(0, 0, 350, 100));
        whishListPanel.setLayout(new BoxLayout(whishListPanel, BoxLayout.Y_AXIS));
        whishListPanel.setBackground(UI_BG_COLOR);
        for (Toy toy : order.getToys()) {
            JCheckBox checkBox = new JCheckBox(toy.getName());
            checkBox.setSelected(true);
            checkBox.setEnabled(false);
            checkBox.setBackground(UI_BG_COLOR);
            checkBox.setForeground(UI_TEXT_COLOR);
            whishListPanel.add(checkBox);
            this.checkBoxes.add(checkBox);
        }
        _inContentPanel.add(whishListPanel);

        return _inContentPanel;
    }

    public PackagingElf getPackagingElf() {
        return packagingElf;
    }

    public DefaultListModel<Order> getListForValidOrders() {
        return listModel;
    }

    public CardLayout getCardLayoutForContentPanel() {
        return cardLayoutForContentPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public ArrayList<JCheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    public void stopDisplayingOrder(Order order) {
        // Remove order from displayed list (stop displaying order)
        getListForValidOrders().removeElement(order);

        // Remove current card from contentPanel
        Component panel = null;
        int j=0; // iterator
        do { // get current card
            panel = getContentPanel().getComponents()[j];
            if (panel.isVisible()) {
                break;
            }
            panel = null; j++;
        } while (j < getContentPanel().getComponents().length);
        if (panel != null) { // remove current card
            getCardLayoutForContentPanel().removeLayoutComponent(panel);
        }
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
