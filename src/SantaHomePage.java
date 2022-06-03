import javax.swing.*;
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

    /* OrdersManager that provides all the orders in the database */
    private final OrdersManager ordersManager;

    /* List of received orders */
    private JList<Order> list;
    private JPanel ongoingOrdersPage;
    private JPanel validatedOrdersPage;
    private JPanel readyOrdersPage;
    private JPanel packagingElfPage;
    private JPanel healingElfPage;
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
    private ArrayList<JCheckBox> validatedToys= new ArrayList<JCheckBox>();


    public SantaHomePage(OrdersManager manager) {
        super("Page d'accueil du Père Noël");
        this.ordersManager = manager;
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
        this.getContentPane().add("5", healingElfPage);
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
        this.ongoingOrdersPage.add(createListPanel(ordersManager.getReceivedOrders()));
        this.ongoingOrdersPage.add(createContentPanel(ordersManager.getReceivedOrders()));

        // validated
        this.validatedOrdersPage = new JPanel();
        this.validatedOrdersPage.setBackground(Color.GRAY);

        // ready
        this.readyOrdersPage = new JPanel();
        this.readyOrdersPage.setBackground(UI_BG_COLOR_2);
    }

    private JPanel createListPanel(ArrayList<Order> managersList) {
        JPanel listPanel = new JPanel();
        listPanel.setBackground(Color.BLUE);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.add(new UILabel("Commandes reçues"));

        DefaultListModel<Order> listModel = new DefaultListModel<Order>();
        this.list = new JList<Order>(listModel);
        this.list.setCellRenderer(new UIListCellRenderer());
        for (Order order : managersList) {
            listModel.addElement(order);
        }
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new LineBorder(UI_BORDER_COLOR, 1, true));
        this.list.setLayoutOrientation(JList.VERTICAL);
        this.list.setEnabled(false);
        this.list.setBackground(UI_BG_COLOR_2);
        this.list.setForeground(UI_TEXT_COLOR);
        scrollPane.setViewportView(this.list);
        listPanel.add(scrollPane);

        return listPanel;
    }

    public JPanel createContentPanel(ArrayList<Order> managersList) {
        contentPanel = new JPanel();
        contentPanel.setLayout(cardLayoutForContentPanel);
        contentPanel.setBackground(Color.GREEN);

        for (Order order : managersList) {
            contentPanel.add(order.getOwner().getEmail().toString(), create_InContentPanel(order));
        }
        //new ShowOrderContentThread(this).start();

        return contentPanel;
    }

    private JPanel create_InContentPanel(Order order) {
        JPanel _inContentPanel = new JPanel();
        _inContentPanel.setName(order.getOwner().getEmail().toString());
        _inContentPanel.setLayout(new BoxLayout(_inContentPanel, BoxLayout.Y_AXIS));

        JPanel personalInfosPanel = new JPanel();
        personalInfosPanel.setBackground(Color.DARK_GRAY);
        personalInfosPanel.add(new UILabel(order.getOwner().toString()));
        _inContentPanel.add(personalInfosPanel);

        JPanel whishListPanel = new JPanel();
        whishListPanel.setLayout(new BoxLayout(whishListPanel, BoxLayout.Y_AXIS));
        whishListPanel.setBackground(Color.CYAN);
        for (Toy toy : order.getToys()) {
            JCheckBox checkBox = new JCheckBox(toy.getName());
            whishListPanel.add(checkBox);
            this.validatedToys.add(checkBox);
        }
        _inContentPanel.add(whishListPanel);

        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.ORANGE);
        UITextArea message = new UITextArea();
        message.setText("Message : \n" + order.getMessage());
        message.setEditable(false);
        messagePanel.add(message);
        _inContentPanel.add(messagePanel);

        JPanel buttonPanel = new JPanel();
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
        _inContentPanel.add(buttonPanel);

        return _inContentPanel;
    }

    private void configElfPages() {
        this.packagingElfPage = new JPanel();
        this.packagingElfPage.setBackground(Color.ORANGE);

        this.healingElfPage = new JPanel();
        this.healingElfPage.setBackground(Color.BLUE);
    }

    private void configReindersPage() {
        this.reindersPage = new JPanel();
        this.reindersPage.setBackground(Color.CYAN);
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
    public ArrayList<JCheckBox> getValidatedToys() {
        return validatedToys;
    }

    public JList<Order> getList() {
        return this.list;
    }

    public OrdersManager getOrdersManager() {
        return this.ordersManager;
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
