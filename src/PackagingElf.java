/**
 * <h1>PackagingElf</h1>
 * Elf that prepare validated orders.*/
public class PackagingElf extends Elf {

    /* Order manager used by packagingElf */
    private OrdersManager ordersManager;

    /**
     * Create a new {@link PackagingElf}.*/
    public PackagingElf (String fName, String lName, Email email, String pwd, EmploymentStatus status){
        super(fName, lName, email, pwd, status);
        this.save();
    }

    /**
     * Load an existing {@link PackagingElf} from database.*/
    public PackagingElf (Email mail){
        super(mail);
        PackagingElf loadedElf = (PackagingElf) FileHelper.load("/home/paul/Documents/java/SantaAppProject/AppDataBase/UsersFiles.santaDB/" + mail.toString());
        super.setFirstName(loadedElf.getFirstName());
        super.setLastName(loadedElf.getLastName());
        super.setPassword(loadedElf.getPassword());
        super.setEmploymentStatus(loadedElf.getStatus());
        this.ordersManager = loadedElf.getOrdersManager();
    }

    public void setOrdersManager(OrdersManager manager){
        this.ordersManager = manager;
    }

    public OrdersManager getOrdersManager(){
        return this.ordersManager;
    }
}
