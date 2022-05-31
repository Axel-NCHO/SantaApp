public class TestOrder {
    public static void main(String[] args){
        //Order test = new Order(new Child(new Email("naxelamour09@gmail.com")), "maiçage", OrderState.ONGOING, null);
        OrdersManager om = new OrdersManager(true);
        System.out.println(om.getReceivedOrders().get(0).getMessage());
    }
}
