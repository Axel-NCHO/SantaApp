import java.io.File;
import java.util.ArrayList;

public class OrdersManager {
    private static ArrayList<Order> receivedOrders;
    private static ArrayList<Order> validOrders;
    private  static ArrayList<Order> preparedOrders;
    private static final String orderPath = "AppDataBase/Orders.santaDB/";

    public OrdersManager (){
        receivedOrders = new ArrayList<Order>();
        validOrders = new ArrayList<Order>();
        preparedOrders = new ArrayList<Order>();
    }

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

    public static void addOrder (Order order){
        if(!order.getOwner().getHasOrdered()){
            order.setState(OrderState.ONGOING);
            receivedOrders.add(order);
            order.getOwner().setHasOrdered(true);
            FileHelper.export(orderPath + "Ongoing/" + order.getOwner().getEmail().toString(),order);
        }
    }

    public static void setValid (Order order){
        order.setState(OrderState.VALIDATED);
        receivedOrders.remove(order);
        validOrders.add(order);
        FileHelper.remove(orderPath + "Ongoing/" + order.getOwner().getEmail().toString());
        FileHelper.export(orderPath + "Validated/" + order.getOwner().getEmail().toString(),order);
    }

    public static void setPrepared (Order order){
        order.setState(OrderState.PREPARED);
        validOrders.remove(order);
        preparedOrders.add(order);
        FileHelper.remove(orderPath + "Validated/" + order.getOwner().getEmail().toString());
        FileHelper.export(orderPath + "Ready/" + order.getOwner().getEmail().toString(),order);
    }

    public ArrayList<Order> getReceivedOrders(){
        return this.receivedOrders;
    }

    public ArrayList<Order> getPreparedOrders() {
        return preparedOrders;
    }

    public ArrayList<Order> getValidOrders() {
        return validOrders;
    }

    public static void cancelOrder(Order order){
        receivedOrders.remove(order);
        FileHelper.remove(orderPath + "Ready/" + order.getOwner().getEmail().toString());
    }

    public static void removeToy(Order order, Toy toy){
        order.removeToy(toy);
    }
}
