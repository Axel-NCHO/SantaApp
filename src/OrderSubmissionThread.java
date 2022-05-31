import javax.swing.*;
import java.util.ArrayList;

public class OrderSubmissionThread extends Thread implements UiFinals{

    private NewChildHomePage page;
    private Order order;
    public OrderSubmissionThread (NewChildHomePage page){
        this.page = page;
    }

    @Override
    public void run(){
        try {
            page.getNbChoosenToys().setAvailableForRead();
            if (Integer.parseInt(page.getNbChoosenToys().read()) <= MAX_NB_TOYS_PER_ORDER) {
                Order newOrder = new Order(page.getChild(), page.getMessage().getText(), OrderState.ONGOING, (ArrayList<Toy>) page.getListOfAvailableToys().getSelectedValuesList());
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
