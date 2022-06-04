import javax.swing.*;

public class OrderCancellationThread extends Thread {

    private SantaHomePage page;

    public OrderCancellationThread(SantaHomePage page) {
        this.page = page;
    }
    @Override
    public void run() {
        // Find the right order to cancel by using the child's email
        // because each card in contentPanel has a child's emails as name
        String childEmail = new FindOrderByEmailThread(page).startThread();

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

        if (order != null) {
            page.getSanta().getOrdersManager().cancelOrder(order);
            JOptionPane.showMessageDialog(page, "Commande annulée.");
        }
    }
}
