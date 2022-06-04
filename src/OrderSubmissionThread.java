import javax.swing.*;
import java.util.ArrayList;

/**
 * <h1>OrderSubmissionThread</h1>
 * {@link Thread} for submitting (sending) an {@link Order}.
 * If the {@link Child} chose the right amount of toys, the order is
 * automatically saved in database in Ongoing repo,
 * waiting for Santa's validation.
 * Also, the Child {@link Object} corresponding is updated with
 * attribute 'hasOrdered' passed to true.*/
public class OrderSubmissionThread extends Thread implements UiFinals{

    private final NewChildHomePage page;
    private Order order;
    public OrderSubmissionThread (NewChildHomePage page){
        this.page = page;
    }

    @Override
    public void run(){
        try {
            page.getNbChosenToys().setAvailableForRead();
            if (Integer.parseInt(page.getNbChosenToys().read()) <= MAX_NB_TOYS_PER_ORDER) {
                Child child = new Child(page.getChild().getEmail());
                child.setHasOrdered(true);
                child.save();
                Order newOrder = new Order(child, page.getMessage().getText(), OrderState.ONGOING, (ArrayList<Toy>) page.getListOfAvailableToys().getSelectedValuesList());
                JOptionPane.showMessageDialog(page, "Commande envoyée !");
                this.order = newOrder;
            } else {
                JOptionPane.showMessageDialog(page, "Vous avez droit à 5 cadeaux au maximum.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Order startThread() {
        run();
        return this.order;
    }
}
