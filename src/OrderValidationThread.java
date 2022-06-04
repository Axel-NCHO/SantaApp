import javax.swing.*;
import java.awt.*;

/**
 * <h1>OrderValidationThread</h1>
 * {@link Thread} for validating one order.*/
public class OrderValidationThread extends Thread {

    private SantaHomePage page;

    public OrderValidationThread(SantaHomePage page) {
        this.page = page;
    }

    @Override
    public void run() {
        // Find the right order to validate or cancel by using the child's email
        // because each card in contentPanel has a child's emails as name
        String childEmail = new FindOrderByEmailThread(page).startThread();

        // Search the right order to validate in manager's list
        /* We do not use a 'for' loop because :
        *   -> we do not know the position of the order in the list
        *   -> modifying the order while iterating on the list would cause a ConcurrentModificationException.
        * A 'do .. while..' loop allows to find the order, save the reference and stop iterating
        * on the list, then modify the order.*/
        Order order = null;
        int i=0; // iterator
        do {
            order = page.getSanta().getOrdersManager().getReceivedOrders().get(i);
            if (order.getOwner().getEmail().toString().equals(childEmail)) {
                break;
            }
            order = null;
            i++;
        } while (i < page.getSanta().getOrdersManager().getReceivedOrders().size());

        // Verify the gentleness of the child and remove toys not selected by santa from the order
        if (order != null) {
            int amount = getNbToysToValidate(order);
            for (JCheckBox checkBox : page.getCheckBoxes()) {
                if (!checkBox.isSelected()) {
                    page.getSanta().getOrdersManager().removeToy(order, new Toy(checkBox.getText()));
                }
            }
            if (order.getToys().size() != amount) {
                JOptionPane.showMessageDialog(page, "Vous devez valider " + amount + " jouets.", "Erreur", JOptionPane.ERROR_MESSAGE);
                reload(order);
            } else {
                if (order.getToys().size() != 0) {
                    // move order file to valid orders repo
                    page.getSanta().getOrdersManager().setValid(order);
                    JOptionPane.showMessageDialog(page, "Commande validée.");

                    // Display order in valid orders section
                    page.getListForValidOrders().addElement(order);

                    page.stopDisplayingOrder(order);
                } else {
                    // Notify to use cancel button
                    JOptionPane.showMessageDialog(page, """
                                    Aucun jouet sélectionné. Utilisez le bouton 'Annuler la commande'
                                    pour annuler la commande""","Erreur", JOptionPane.ERROR_MESSAGE);
                    // Reload the order from database
                    reload(order);
                }
            }

        }
    }

    private int getNbToysToValidate(Order order) {
        int result;
        switch (order.getOwner().getGentleness()) {
            case GOOD_BOY -> result = 2;
            case NICE_BOY -> result = 1;
            case BAD_BOY -> result = 0;
            default -> result = order.getToys().size();
        }
        return result;
    }

    private void reload(Order order) {
        page.getSanta().getOrdersManager().getReceivedOrders().remove(order);
        page.getSanta().getOrdersManager().getReceivedOrders().add(new Order(order.getOwner().getEmail(), OrderState.ONGOING));
    }
}
