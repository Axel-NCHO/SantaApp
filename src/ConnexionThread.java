import javax.swing.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class ConnexionThread extends Thread {

    private ConnexionPage page ;

    public ConnexionThread(ConnexionPage page) {
        this.page = page;
    }

    @Override
    public void run() {
        if (new Email(page.getEmail().getText()).isValidEmail()) {
            OrdersManager manager = new OrdersManager(true);
            if (page.getEmail().getText().endsWith("@pelf.com")) {
                PackagingElf elf = new PackagingElf(new Email(page.getEmail().getText()));
                if (elf.getPassword().equals(page.getPwd().getText())) {
                    elf.setOrdersManager(manager);
                    new Thread() {
                        public void run() {
                            PackagingElfHomePage packagingElfHomePage = new PackagingElfHomePage(elf);
                            packagingElfHomePage.run();
                        }
                    }.start();
                    new CloseWindowThread(page).start();
                } else {
                    JOptionPane.showMessageDialog(page, "Mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (page.getEmail().getText().equals("santa@santa.com")) {
                Santa santa = new Santa(new Email("santa@santa.com"));
                if (santa.getPassword().equals(page.getPwd().getText())) {
                    santa.setOrdersManager(manager);
                    new Thread() {
                        public void run() {
                            SantaHomePage santaHomePage = new SantaHomePage(santa);
                            santaHomePage.run();
                            new WelcomeThread(santaHomePage).start();
                        }
                    }.start();
                    new CloseWindowThread(page).start();
                } else {
                    JOptionPane.showMessageDialog(page, "Mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            }
            else {
                Order order = null;
                int i=0; //iterator
                // Searching in received orders
                if (manager.getReceivedOrders().size() != 0) {
                    do {
                        order = manager.getReceivedOrders().get(i);
                        if (order.getOwner().getEmail().equals(new Email(page.getEmail().getText()))) {
                            break;
                        }
                        order = null;
                        i++;
                    } while (i < manager.getReceivedOrders().size());
                }
                if (order == null) {
                    // Searching in validated orders
                    if (manager.getValidOrders().size() != 0) {
                        i = 0;
                        do {
                            order = manager.getValidOrders().get(i);
                            if (order.getOwner().getEmail().equals(new Email(page.getEmail().getText()))) {
                                break;
                            }
                            order = null;
                            i++;
                        } while (i < manager.getValidOrders().size());
                    }
                    if (order == null) {
                        // Searching in ready orders
                        if (manager.getPreparedOrders().size() != 0) {
                            i = 0;
                            do {
                                order = manager.getPreparedOrders().get(i);
                                if (order.getOwner().getEmail().equals(new Email(page.getEmail().getText()))) {
                                    break;
                                }
                                order = null;
                                i++;
                            } while (i < manager.getPreparedOrders().size());
                        }
                        if (order == null) {
                            if (Arrays.toString(FileHelper.fileList("AppDataBase/UsersFiles.santaDB")).contains(page.getEmail().getText())) {
                                JOptionPane.showMessageDialog(page, "Commande supprimée.");
                            } else {
                                JOptionPane.showMessageDialog(page, "Utilisateur inconnu", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
                if (order != null) {
                    if (page.getPwd().getText().equals(order.getOwner().getPassword())) {
                        new OrderCheckingThread(order).start();
                        new CloseWindowThread(page).start();
                    } else {
                        JOptionPane.showMessageDialog(page, "Mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(page, "Email non valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
