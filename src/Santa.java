/**
 * <h1>Santa</h1>
 * <hr>
 * Santa Claus. It is the {@link User} with the highest access level.
 * He validates or cancels orders, removes toys from an {@link Order} and
 * manages {@link Elf}s and stocks.
 * The user 'Santa' exists by default when launching the app.
 * He just connects with a special e-mail and a provided password and have
 * access to the whole app.*/

public class Santa extends User {

    /* Managers used by santa to access the whole app. */
    private OrdersManager ordersManager;
    private ReinderCare reinderCare;

    public Santa (String fName, String lName, Email email, String pwd){
        super(fName, lName, email, pwd);
        this.save();
    }

    public Santa (Email mail) {
        super(mail);
        Santa santaLoaded = (Santa) FileHelper.load("AppDataBase/UsersFiles.santaDB/" + mail.toString());
        this.setFirstName(santaLoaded.getFirstName());
        this.setLastName(santaLoaded.getLastName());
        this.setPassword(santaLoaded.getPassword());
        this.ordersManager = santaLoaded.getOrdersManager();
        this.reinderCare = santaLoaded.getReinderCare();
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
