import java.io.File;
import java.util.ArrayList;

public class OrdersManager {
    private ArrayList<Order> receivedOrders;
    private ArrayList<Order> validOrders;
    private  ArrayList<Order> preparedOrders;

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
            String[] Ongoing = new File("AppDataBase/Orders.santaDB/Ongoing/").list();
            String[] Validated = new File("AppDataBase/Orders.santaDB/Validated/").list();
            String[] Ready = new File("AppDataBase/Orders.santaDB/Ready/").list();
            for(int i = 0; i < Ongoing.length; i++){
                receivedOrders.add((Order) FileHelper.load("AppDataBase/Orders.santaDB/Ongoing/" + Ongoing[i]));
            }
            for(int i = 0; i < Validated.length; i++){
                validOrders.add((Order) FileHelper.load("AppDataBase/Orders.santaDB/Validated/" + Validated[i]));
            }
            for(int i = 0; i < Ready.length; i++){
                preparedOrders.add((Order) FileHelper.load("AppDataBase/Orders.santaDB/Ready/" + Ready[i]));
            }
        }
    }

    public void addOrder (Order order){
        if(!order.getOwner().getHasOrdered()){
            order.setState(OrderState.ONGOING);
            receivedOrders.add(order);
            order.getOwner().setHasOrdered(true);
            FileHelper.export("AppDataBase/Orders.santaDB/Ongoing/" + order.getOwner().getEmail().toString(),order);
        }
    }

    public void setValid (Order order){
        order.setState(OrderState.VALIDATED);
        receivedOrders.remove(order);
        validOrders.add(order);
        FileHelper.remove("AppDataBase/Orders.santaDB/Ongoing/" + order.getOwner().getEmail().toString());
        FileHelper.export("AppDataBase/Orders.santaDB/Validated/" + order.getOwner().getEmail().toString(),order);
    }

    public void setPrepared (Order order){
        order.setState(OrderState.PREPARED);
        validOrders.remove(order);
        preparedOrders.add(order);
        FileHelper.remove("AppDataBase/Orders.santaDB/Validated/" + order.getOwner().getEmail().toString());
        FileHelper.export("AppDataBase/Orders.santaDB/Ready/" + order.getOwner().getEmail().toString(),order);
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

    public void cancelOrder(Order order){
        receivedOrders.remove(order);
        FileHelper.remove("AppDataBase/Orders.santaDB/Ready/" + order.getOwner().getEmail().toString());
    }

    public void removeToy(Order order, Toy toy){
        order.removeToy(toy);
    }
}
