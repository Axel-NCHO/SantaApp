import javax.swing.*;

public class ConnexionThread extends Thread {

    private ConnexionPage page ;

    public ConnexionThread(ConnexionPage page) {
        this.page = page;
    }

    @Override
    public void run() {
        if (new Email(page.getEmail().getText()).isValidEmail()) {
            if (page.getEmail().getText().endsWith("@elf.com")) {}
            else if (page.getEmail().getText().endsWith("@santa.com")) {}
            else {
                OrdersManager manager = new OrdersManager(true);
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
                            JOptionPane.showMessageDialog(page, "Utilisateur inconnu.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
