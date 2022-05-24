import java.util.Date;

public class Test {

    public static void main(String[] args){

        /*OrdersManager manager = new OrdersManager();
        Email mail = new Email("naxelamour09@gmail.com");
        Email mail2 = new Email("moijoan99@gmail.com");
        Child Axel = new Child("Axel", "Ncho", mail, "mangerOuEtreMangé", new Date(), "6 Penford Drive", 5);
        Axel.setHasOrdered(false);
        Order AxelsOrder = new Order(Axel, "Salut Père Noël !", OrderState.ONGOING, new Toy("AK47", 5), new Toy("PS5", 5), new Toy("PS5", 5), new Toy("PS5", 5), new Toy("PS5", 5));
        manager.addOrder(AxelsOrder);
        Axel.setHasOrdered(true);
        try {
            ExistingChildHomePage page = new ExistingChildHomePage(Axel);
            page.setWishList(AxelsOrder.getToys());
            page.setMessage(AxelsOrder.getMessage());
            page.selectSentCheckBox();
            page.run();
        }
        catch (Exception e) {
            //System.out.println("Utilisateur " + mail.toString() + " inexistant.");
            e.printStackTrace();
        }*/

        RegistrationPage page = new RegistrationPage();
        page.run();
    }
}
