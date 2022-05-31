public class OrderCheckingThread extends Thread {

    private Order order;

    public OrderCheckingThread (Order order) {
        this.order = order;
    }

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
