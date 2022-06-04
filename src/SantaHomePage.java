import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * <h1>SantaHomePage</h1>
 * Page used by {@link Santa} to manage the whole app.
 * <hr>
 *
 *  It contains one main contentPane panel that implements a {@link CardLayout}.
 *  The cardLayout allows to remain in the same frame while navigating in the
 *  {@link JMenuBar} menu bar.
 * <hr>*/
public class SantaHomePage extends UIPage {

    /* Santa owner of this page loaded from users database */
    private final Santa santa;

    /* List models for displayed orders */
    private DefaultListModel<Order> listModelForReceivedOrders = new DefaultListModel<Order>();
    private DefaultListModel<Order> listModelForValidOrders = new DefaultListModel<Order>();
    private DefaultListModel<Order> listModelForReadyOrders = new DefaultListModel<Order>();

    /* Page for received orders */
    private JPanel ongoingOrdersPage;

    /* Page for validated orders */
    private JPanel validatedOrdersPage;

    /* Page for ready orders */
    private JPanel readyOrdersPage;

    /* Page for packaging elfs management */
    private JPanel packagingElfPage;

    /* Page for medical elfs management */
    private JPanel medElfPage;

    /* Page for reinder care management */
    private JPanel reindersPage;
    private final JMenuBar menubar = new JMenuBar();

    /* The card manager of this page. It allows to have multiple panels in the
    * main frame and show one of them at a time (navigate on the page via the
    * menu bar). */
    private final CardLayout cardLayout = new CardLayout();

    /* The card manager of the contentPanel. It allows to load the content of each
    order in the list and show one of them at a time by pressing 'précédent' or
    'suivant' button. */
    private final CardLayout cardLayoutForContentPanel = new CardLayout();

    /* Panel managed by the previous cardLayoutForContentPanel. It displays the content of
    * one order at a time*/
    private JPanel contentPanel;

    /* List of JCheckboxes. It contains the displayed JCheckboxes. Those represent the toys in
    * an order. This list allows to know which toys are not validated by santa so that they can
    * be removed from the order.*/
    private ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();


    public SantaHomePage(Santa santa) {
        super("Page d'accueil du Père Noël");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.santa = santa;
        this.getContentPane().setLayout(cardLayout);
        configMenubar();
        this.setJMenuBar(menubar);
        configOrdersPages();
        configElfPages();
        configReindersPage();
        this.getContentPane().add("1", ongoingOrdersPage);
        this.getContentPane().add("2", validatedOrdersPage);
        this.getContentPane().add("3", readyOrdersPage);
        this.getContentPane().add("4", packagingElfPage);
        this.getContentPane().add("5", medElfPage);
        this.getContentPane().add("6", reindersPage);
    }

    private void configMenubar() {
        // Orders
        JMenu orders = new JMenu("Commandes");

        JMenuItem ongoingOrdersPageAccess = new JMenuItem("Commandes en attente de validation");
        ongoingOrdersPageAccess.addActionListener(new UIActionListener(this));
        orders.add(ongoingOrdersPageAccess);

        JMenuItem validatedOrdersPageAccess = new JMenuItem("Commandes validées");
        validatedOrdersPageAccess.addActionListener(new UIActionListener(this));
        orders.add(validatedOrdersPageAccess);

        JMenuItem readyOrdersPageAccess = new JMenuItem("Commandes prêtes");
        readyOrdersPageAccess.addActionListener(new UIActionListener(this));
        orders.add(readyOrdersPageAccess);

        this.menubar.add(orders);

        // Elf
        JMenu elf = new JMenu("Lutins");

        JMenuItem packagingElfAccess = new JMenuItem("Lutins préparateurs");
        packagingElfAccess.addActionListener(new UIActionListener(this));
        elf.add(packagingElfAccess);

        JMenuItem healingElfAccess = new JMenuItem("Lutins soigneurs");
        healingElfAccess.addActionListener(new UIActionListener(this));
        elf.add(healingElfAccess);

        this.menubar.add(elf);

        // Reinders
        JMenu reinders = new JMenu("Rennes");
        JMenuItem reindersAccess = new JMenuItem("Gestion des rennes");
        reindersAccess.addActionListener(new UIActionListener(this));
        reinders.add(reindersAccess);

        this.menubar.add(reinders);
    }

    private void configOrdersPages() {
        // ongoing
        this.ongoingOrdersPage = new JPanel();
        this.ongoingOrdersPage.setBackground(UI_BG_COLOR);
        this.ongoingOrdersPage.setLayout(new GridLayout(1, 2));
        this.ongoingOrdersPage.add(createListPanel("Commandes Reçues", santa.getOrdersManager().getReceivedOrders(), listModelForReceivedOrders));
        this.ongoingOrdersPage.add(createContentPanel(santa.getOrdersManager().getReceivedOrders()));

        // validated
        this.validatedOrdersPage = new JPanel();
        this.validatedOrdersPage.setBackground(UI_BG_COLOR);
        this.validatedOrdersPage.setLayout(new BorderLayout());
        this.validatedOrdersPage.add(createListPanel("Commandes validées", santa.getOrdersManager().getValidOrders(), listModelForValidOrders));

        // ready
        this.readyOrdersPage = new JPanel();
        this.readyOrdersPage.setBackground(UI_BG_COLOR);
        this.readyOrdersPage.setLayout(new BorderLayout());
        this.readyOrdersPage.add(createListPanel("Commandes prêtes", santa.getOrdersManager().getPreparedOrders(), listModelForReadyOrders));
    }

    /**
     * Create a panel that displays the elements in one list of an {@link OrdersManager}.
     * @param title displayed title at top in the panel.
     * @param managersList the list to be displayed.
     * @param plistModel {@link DefaultListModel}.*/
    private JPanel createListPanel(String title, ArrayList<Order> managersList, DefaultListModel<Order> plistModel) {
        JPanel listPanel = new JPanel();
        listPanel.setBackground(UI_BG_COLOR_2);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.add(new UILabel(title));

        /* List of received orders */
        JList<Order> list = new JList<Order>(plistModel);
        list.setCellRenderer(new UIListCellRenderer());
        for (Order order : managersList) {
            plistModel.addElement(order);
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
        JButton previous = new JButton("Précédent");
        previous.addActionListener(new UIActionListener(this));
        JButton validateButton = new JButton("Valider");
        validateButton.addActionListener(new UIActionListener(this));
        JButton cancelButton = new JButton("Annuler la commande");
        cancelButton.addActionListener(new UIActionListener(this));
        JButton next = new JButton("Suivant");
        next.addActionListener(new UIActionListener(this));
        buttonPanel.add(previous);
        buttonPanel.add(validateButton);
        buttonPanel.add(cancelButton);
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
        //new ShowOrderContentThread(this).start();

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
        whishListPanel.setBorder(new EmptyBorder(0, 0, 0, 200));
        whishListPanel.setLayout(new BoxLayout(whishListPanel, BoxLayout.Y_AXIS));
        whishListPanel.setBackground(UI_BG_COLOR);
        for (Toy toy : order.getToys()) {
            JCheckBox checkBox = new JCheckBox(toy.getName());
            checkBox.setBackground(UI_BG_COLOR);
            checkBox.setForeground(UI_TEXT_COLOR);
            whishListPanel.add(checkBox);
            this.checkBoxes.add(checkBox);
        }
        _inContentPanel.add(whishListPanel);

        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(UI_BG_COLOR);
        UITextArea message = new UITextArea();
        message.setText("Message : \n" + order.getMessage());
        message.setEditable(false);
        messagePanel.add(message);
        _inContentPanel.add(messagePanel);

        /*JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(UI_BG_COLOR);
        JButton previous = new JButton("Précédent");
        previous.addActionListener(new UIActionListener(this));
        JButton validateButton = new JButton("Valider");
        validateButton.addActionListener(new UIActionListener(this));
        JButton cancelButton = new JButton("Annuler la commande");
        cancelButton.addActionListener(new UIActionListener(this));
        JButton next = new JButton("Suivant");
        next.addActionListener(new UIActionListener(this));
        buttonPanel.add(previous);
        buttonPanel.add(validateButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(next);
        _inContentPanel.add(buttonPanel);*/

        return _inContentPanel;
    }

    private void configElfPages() {
        this.packagingElfPage = new JPanel();
        this.packagingElfPage.setBackground(Color.ORANGE);

        this.medElfPage = new JPanel();
        this.medElfPage.setBackground(Color.BLUE);
    }

    private void configReindersPage() {
        this.reindersPage = new JPanel();
        this.reindersPage.setBackground(Color.CYAN);
    }

    public Santa getSanta() {
        return santa;
    }

    public CardLayout getMainCardLayout() {
        return this.cardLayout;
    }

    public CardLayout getCardLayoutForContentPanel() {
        return this.cardLayoutForContentPanel;
    }

    public JPanel getContentPanel() {
        return this.contentPanel;
    }
    public ArrayList<JCheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    public DefaultListModel<Order> getListOfReceivedOrders() {
        return this.listModelForReceivedOrders;
    }

    public DefaultListModel<Order> getListForValidOrders() {
        return this.listModelForValidOrders;
    }

    public void stopDisplayingOrder(Order order) {
        // Stop displaying order
        getListOfReceivedOrders().removeElement(order);

        // Remove current card from contentPanel
        // Same reason for using a 'do .. while ..' loop as previously
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
