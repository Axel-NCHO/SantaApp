public class Santa extends User {

    private OrdersManager ordersManager;
    private ReinderCare reinderCare;

    public Santa (String fName, String lName, Email email, String pwd){
        super(fName, lName, email, pwd);
    }

    public void setOrdersManager(OrdersManager manager){
        this.ordersManager = manager;
    }

    public void setReinderCare(ReinderCare manager){
        this.reinderCare = manager;
    }

    public OrdersManager getOrdersManager() {
        return ordersManager;
    }

    public ReinderCare getReinderCare() {
        return reinderCare;
    }
}
