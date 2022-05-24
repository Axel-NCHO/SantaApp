public abstract class Elf extends User{

    private EmploymentStatus status;

    public Elf(String fn, String ln, Email mail, String pw, EmploymentStatus st) {
        super(fn, ln, mail, pw);
        status = st;
    }

    public Elf(Email mail){
        super(mail);
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
