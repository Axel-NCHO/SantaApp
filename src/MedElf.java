public class MedElf extends Elf {

    private ReinderCare reinderCare;

    public MedElf (String fName, String lName, Email email, String pwd, EmploymentStatus status){
        super(fName, lName, email, pwd, status);
        this.save();
    }

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
