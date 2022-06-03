import java.io.File;
import java.util.ArrayList;

/**
 * <h1>OrdersManager</h1>
 * Manage orders. Must be used for any operations on an order.*/
public class OrdersManager {
    /* List of received (but not validated) orders */
    private static ArrayList<Order> receivedOrders;

    /* List of validated orders */
    private static ArrayList<Order> validOrders;

    /* List of orders that are ready for delivery */
    private  static ArrayList<Order> preparedOrders;

    /* Path to orders database */
    private static final String orderPath = "AppDataBase/Orders.santaDB/";

    /**
     * Create a new order manager with empty lists.
     * Can be used for saving a new order.*/
    public OrdersManager (){
        receivedOrders = new ArrayList<Order>();
        validOrders = new ArrayList<Order>();
        preparedOrders = new ArrayList<Order>();
    }

    /**
     * Create a new order manager that knows all the orders in the database.
     * Can be used for displaying an existing order or modify it.*/
    public OrdersManager(Boolean load){
        if(load){
            receivedOrders = new ArrayList<Order>();
            validOrders = new ArrayList<Order>();
            preparedOrders = new ArrayList<Order>();
            String[] Ongoing = new File(FileHelper.getAppPath() +  orderPath + "Ongoing/").list();
            String[] Validated = new File(FileHelper.getAppPath() +  orderPath + "Validated/").list();
            String[] Ready = new File(FileHelper.getAppPath() +  orderPath + "Ready/").list();
            for(int i = 0; i < Ongoing.length; i++){
                Order tmp = (Order) FileHelper.load(orderPath + "Ongoing/" + Ongoing[i]);
                receivedOrders.add(tmp);
            }
            for(int i = 0; i < Validated.length; i++){
                validOrders.add((Order) FileHelper.load(orderPath + "Validated/" + Validated[i]));
            }
            for(int i = 0; i < Ready.length; i++){
                preparedOrders.add((Order) FileHelper.load(orderPath + "Ready/" + Ready[i]));
            }
        }
    }

    /**
     * Send a new order to Santa.
     * The order is saved in the database in the right repo with {@link OrderState} 'ONGOING'.
     * @param order the order to be sent*/
    public void addOrder (Order order){
        if(!order.getOwner().getHasOrdered()){
            order.setState(OrderState.ONGOING);
            receivedOrders.add(order);
            order.getOwner().setHasOrdered(true);
            FileHelper.export(orderPath + "Ongoing/" + order.getOwner().getEmail().toString(),order);
        }
    }

    /**
     * Validate an order.
     * The order is saved in the database in the right repo with {@link OrderState} 'VALIDATED'.
     * @param order the order to be validated*/
    public void setValid (Order order){
        order.setState(OrderState.VALIDATED);
        receivedOrders.remove(order);
        validOrders.add(order);
        FileHelper.remove(orderPath + "Ongoing/" + order.getOwner().getEmail().toString());
        FileHelper.export(orderPath + "Validated/" + order.getOwner().getEmail().toString(),order);
    }

    /**.
     * The order is saved in the database in the right repo with {@link OrderState} 'PREPARED'.
     * @param order the order to be set as ready*/
    public void setPrepared (Order order){
        order.setState(OrderState.PREPARED);
        validOrders.remove(order);
        preparedOrders.add(order);
        FileHelper.remove(orderPath + "Validated/" + order.getOwner().getEmail().toString());
        FileHelper.export(orderPath + "Ready/" + order.getOwner().getEmail().toString(),order);
    }

    /**
     * @return the list of received but not validated orders*/
    public ArrayList<Order> getReceivedOrders(){
        return receivedOrders;
    }

    /**
     * @return the list of validated orders*/
    public ArrayList<Order> getPreparedOrders() {
        return preparedOrders;
    }

    /**
     * @return the list of orders that are ready for delivery*/
    public ArrayList<Order> getValidOrders() {
        return validOrders;
    }

    /**
     * Cancel an {@link Order} from Ongoing order repo.
     * The order is completely removed from database.*/
    public void cancelOrder(Order order){
        receivedOrders.remove(order);
        FileHelper.remove(orderPath + "Ongoing/" + order.getOwner().getEmail().toString());
    }

    /**
     * Remove a {@link Toy} from an {@link Order}.*/
    public void removeToy(Order order, Toy toy){
        order.removeToy(toy);
    }
}
