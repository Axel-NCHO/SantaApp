/**
 * <h1>OrderCheckingThread</h1>
 * {@link Thread} for displaying an {@link ExistingChildHomePage} frame.*/
public class OrderCheckingThread extends Thread {

    /* Order to be displayed */
    private Order order;

    public OrderCheckingThread (Order order) {
        this.order = order;
    }

    /**
     * Display the order and select the right {@link javax.swing.JCheckBox}
     * according to its {@link OrderState} state.*/
    public void  run() {
        ExistingChildHomePage existingChildPage = new ExistingChildHomePage(order.getOwner());
        existingChildPage.setWishList(order.getToys());
        existingChildPage.setMessage(order.getMessage());
        if (order.getState() == OrderState.ONGOING) {existingChildPage.selectSentCheckBox();}
        if (order.getState() == OrderState.VALIDATED) {existingChildPage.selectValidatedCheckBox();}
        if (order.getState() == OrderState.PREPARED) {existingChildPage.selectReadyCheckBox();}
        existingChildPage.run();
    }
}
