import java.io.Serializable;

import java.util.ArrayList;

public class Order implements Serializable {

    public final Integer MAX_NB_TOYS = 5;
    private Integer nbToysInOrder = 0;

    private Child owner;
    private String message;
    private OrderState state;
    private ArrayList<Toy> toys;

    public Order (Child owner, String msg, OrderState stateOfOrder, ArrayList<Toy> toysInOrder){

        this.owner = owner;
        this.message = msg;
        this.state = stateOfOrder;
        this.toys = new ArrayList<Toy>();
        this.toys = toysInOrder;
        this.save();
    }

    public void addToy(Toy toy){
        if (this.nbToysInOrder < this.MAX_NB_TOYS){
            this.toys.add(toy);
            this.nbToysInOrder ++;
        }
    }

    public String getMessage() {
        return message;
    }

    public Child getOwner() {
        return this.owner;
    }

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

    public void removeToy(Toy toy){
        this.toys.remove(toy);
    }

    public ArrayList<Toy> getToys() {
        return toys;
    }

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
}
