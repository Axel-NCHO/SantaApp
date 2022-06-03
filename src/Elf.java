/**
 * <h1>Elf</h1>
 * <u>Abstract</u> class that represents an elf.*/
public abstract class Elf extends User{

    /* The employment status of an elf */
    private EmploymentStatus status;

    /**
     * Create a new elf.*/
    public Elf(String fn, String ln, Email mail, String pw, EmploymentStatus st) {
        super(fn, ln, mail, pw);
        status = st;
    }

    public Elf(Email mail){
        super(mail);
    }

    public Elf(){
        super();
    }

    public EmploymentStatus getStatus(){
        return status;
    }

    public void setEmploymentStatus(EmploymentStatus newStatus){
        status = newStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){return false;}
        if (obj == this){return true;}
        if (!(obj instanceof Elf)){return false;}

        Elf elf = (Elf)obj;
        return this.getEmail() == elf.getEmail();
    }
}
