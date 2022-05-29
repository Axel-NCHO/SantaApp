import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SantaHomePage extends UIPage {

    private JPanel ongoingOrdersPage;
    private JPanel packagingElfPage;
    private JPanel healingElfPage;
    private JPanel reindersPage;

    private JMenuItem ongoingOrdersPageAccess;
    private JMenuItem packagingElfAccess;
    private JMenuItem healingElfAccess;

    private JMenuBar menubar = new JMenuBar();

    public SantaHomePage() {
        super("Page d'accueil du Père Noël");
        this.setLayout(new BorderLayout());
        configMenubar();
        this.setJMenuBar(menubar);
        configOrdersPage();
        configElfPage();
        //configReindersPage();
        this.load(ongoingOrdersPage);
    }

    private void configMenubar() {
        // Orders
        JMenu orders = new JMenu("Commandes");
        this.ongoingOrdersPageAccess = new JMenuItem("Commandes en attente de validation");
        this.ongoingOrdersPageAccess.addActionListener(new UIActionListener(this));
        orders.add(ongoingOrdersPageAccess);
        this.menubar.add(orders);

        // Elf
        JMenu elf = new JMenu("Lutins");

        this.packagingElfAccess = new JMenuItem("Lutins préparateurs");
        this.packagingElfAccess.addActionListener(new UIActionListener(this));
        elf.add(packagingElfAccess);

        this.healingElfAccess = new JMenuItem("Lutins soigneurs");
        this.healingElfAccess.addActionListener(new UIActionListener(this));
        elf.add(healingElfAccess);

        this.menubar.add(elf);
    }

    private void configOrdersPage() {
        this.ongoingOrdersPage = new JPanel();
        this.ongoingOrdersPage.setBackground(UI_BG_COLOR);
    }

    private void configElfPage() {
        this.packagingElfPage = new JPanel();
        this.packagingElfPage.setBackground(Color.ORANGE);

        this.healingElfPage = new JPanel();
        this.healingElfPage.setBackground(Color.BLUE);
    }


    public void clear() {
        this.getContentPane().removeAll();
    }
    public void load(JPanel panel) {
        this.getContentPane().add(panel);
    }

    public JPanel getOngoingOrdersPage(){
        return this.ongoingOrdersPage;
    }

    public JPanel getPackagingElfPage() {
        return packagingElfPage;
    }

    public JPanel getHealingElfPage() {
        return healingElfPage;
    }

    public JPanel getReindersPage() {
        return reindersPage;
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
