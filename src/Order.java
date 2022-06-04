import java.io.Serial;
import java.io.Serializable;

import java.util.ArrayList;

/**
 * <h1>Order</h1>
 * Represents an order.*/
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1286242683;
    public final Integer MAX_NB_TOYS = 5;

    /* Current number of toys in this order */
    private Integer nbToysInOrder = 0;

    private Child owner;
    private String message;
    private OrderState state;

    /* Current list of toys in this order */
    private ArrayList<Toy> toys;

    public Order (Child owner, String msg, OrderState stateOfOrder, ArrayList<Toy> toysInOrder){

        this.owner = owner;
        this.message = msg;
        this.state = stateOfOrder;
        this.toys = new ArrayList<Toy>();
        this.toys = toysInOrder;
        this.save();
    }

    /**
     * Load an existing order from database.*/
    public Order (Email mail, OrderState state) {
        String orderPath = "AppDataBase/Orders.santaDB/";
        Order loadedOrder = null;
        switch (state) {
            case ONGOING -> loadedOrder = (Order) FileHelper.load(orderPath + "Ongoing/" + mail.toString());
            case VALIDATED -> loadedOrder = (Order) FileHelper.load(orderPath + "Validated/" + mail.toString());
            case PREPARED -> loadedOrder = (Order) FileHelper.load(orderPath + "Ready/" + mail.toString());
        }
        this.owner = loadedOrder.getOwner();
        this.message = loadedOrder.getMessage();
        this.state = loadedOrder.getState();
        this.toys = loadedOrder.getToys();
    }

    public void addToy(Toy toy){
        if (this.nbToysInOrder < this.MAX_NB_TOYS){
            this.toys.add(toy);
            this.nbToysInOrder ++;
        }
    }

    /**
     * @return the message written by a {@link Child}.*/
    public String getMessage() {
        return message;
    }

    /**
     * @return the {@link Child} that made this {@link Order}.*/
    public Child getOwner() {
        return this.owner;
    }

    /**
     * @return the state of this {@link Order}.*/
    public OrderState getState(){
        return this.state;
    } 

    public void setOwner (Child child){
        this.owner = child;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    /**
     * Remove a {@link Toy} form this order.*/
    public void removeToy(Toy toy){
        this.toys.remove(toy);
    }

    /**
     * @return the current list of toys in this {@link Order}.*/
    public ArrayList<Toy> getToys() {
        return toys;
    }

    /**
     * Save an {@link Order} in the database.*/
    public void save() {
        FileHelper.export("AppDataBase/Orders.santaDB/Ongoing/" + this.getOwner().getEmail().toString(),this);
    }

    public boolean equals(Object obj){
        if (obj == null){return false;}
        if (obj == this){return true;}
        if (!(obj instanceof Order)){return false;}
        Order order = (Order)obj;
        return order.getOwner().equals(this.getOwner());

    }

    public String toString() {
        String order = String.format("%s \n%d ans \n%s ", this.getOwner().getLastName(), this.getOwner().getAge(), this.getOwner().getAddress());
        return order;
    }
}
