/**
 * <h1>MedElf</h1>
 * Medical Elf. Elf that take care of the reinders.*/
public class MedElf extends Elf {

    /* ReinderCare used by a medical elf */
    private ReinderCare reinderCare;

    /**
     * Create a new MedElf.*/
    public MedElf (String fName, String lName, Email email, String pwd, EmploymentStatus status){
        super(fName, lName, email, pwd, status);
        this.save();
    }

    /**
     * Load an existing MedElf.*/
    public MedElf (Email mail){
        super(mail);
        MedElf loadedElf = (MedElf) FileHelper.load("AppDataBase/UsersFiles.santaDB/" + mail.toString());
        super.setFirstName(loadedElf.getFirstName());
        super.setLastName(loadedElf.getLastName());
        super.setPassword(loadedElf.getPassword());
        super.setEmploymentStatus(loadedElf.getStatus());
        this.reinderCare = loadedElf.getReinderCare();
    }

    public MedElf(){
        super();
    }

    public void setReinderCare(ReinderCare reinderCare) {
        this.reinderCare = reinderCare;
    }

    public ReinderCare getReinderCare() {
        return reinderCare;
    }
}
