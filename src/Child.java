import java.io.*;
import java.util.Date;

public class Child extends User {
    
    private Date dateOfBirth;
    private String address;
    private Integer minimalAgeToys;
    private Gentleness gentleness;
    private Boolean hasOrdered;

    public Child (String fName, String lName, Email email, String pwd, Date birthDate, String addr, Integer minAgeToys){
        super(fName, lName, email, pwd);
        this.dateOfBirth = birthDate;
        this.address = addr;
        this.minimalAgeToys = minAgeToys;
        FileHelper.export("AppDataBase/UsersFiles.santaDB/" + super.getEmail().toString(),this);
    }

    public Child (Email mail){
        super(mail);
        Child loadedChild = (Child) FileHelper.load("AppDataBase/UsersFiles.santaDB/" + mail.toString());
        super.setFirstName(loadedChild.getFirstName());
        super.setLastName(loadedChild.getLastName());
        super.setPassword(loadedChild.getPassword());
        this.dateOfBirth = loadedChild.getDateOfBirth();
        this.address = loadedChild.getAddress();
        this.minimalAgeToys = loadedChild.getMinimalAgeToys();
        this.hasOrdered = loadedChild.getHasOrdered();
    }

    public Date getDateOfBirth (){
        return this.dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public Integer getMinimalAgeToys() {
        return minimalAgeToys;
    }

    public Gentleness getGentleness (){
        return this.gentleness;
    }

    public Boolean getHasOrdered (){return this.hasOrdered ;}
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setMinimalAgeToys(Integer minimalAgeToys) {
        this.minimalAgeToys = minimalAgeToys;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGentleness(Gentleness gentleness) {
        this.gentleness = gentleness;

    }

    public void setHasOrdered(Boolean hasOrdered){
        this.hasOrdered = hasOrdered;

    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null){return false;}
        if (obj == this){return true;}
        if (!(obj instanceof Child)){return false;}

        Child child = (Child)obj;
        return this.getEmail() == child.getEmail();
    }
}
