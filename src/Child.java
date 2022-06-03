/**
 * <h1>Child</h1>
 * Represents a child.*/
public class Child extends User {

    /* The age of a child */
    private Integer age;

    /* The address of a child */
    private String address;

    /* The gentleness of a child. Determines how many toys a child can have.. */
    private Gentleness gentleness;

    /* Boolean that indicates whether a child has ordered toys. */
    private Boolean hasOrdered;

    /**
     * Create a new child */
    public Child (String fName, String lName, Email email, String pwd, Integer age, String addr, Gentleness gentleness){
        super(fName, lName, email, pwd);
        this.age = age;
        this.address = addr;
        this.gentleness = gentleness;
        this.save();
    }

    /**
     * Load an existing child.*/
    public Child (Email mail){
        super(mail);
        Child loadedChild = (Child) FileHelper.load("AppDataBase/UsersFiles.santaDB/" + mail.toString());
        super.setFirstName(loadedChild.getFirstName());
        super.setLastName(loadedChild.getLastName());
        super.setPassword(loadedChild.getPassword());
        this.age = loadedChild.getAge();
        this.address = loadedChild.getAddress();
        this.hasOrdered = loadedChild.getHasOrdered();
    }

    /**
     * @return The age of a child.*/
    public Integer getAge (){
        return this.age;
    }

    /**
     * @return The address of a child.*/
    public String getAddress() {
        return address;
    }

    /**
     * @return The gentleness of a child.*/
    public Gentleness getGentleness (){
        return this.gentleness;
    }

    /**
     * @return {@link Boolean} that indicates whether a child has ordered toys to Santa.*/
    public Boolean getHasOrdered (){return this.hasOrdered ;}

    public void setAge(Integer age) {
        this.age = age;
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

    /**
     * @return A {@link String} that represents the current state of a {@link Child} object.*/
    @Override
    public String toString () {
        String child = "";
        child += String.format("%s, %d, %s",this.getLastName(), this.getAge(), this.getAddress());
        return child;
    }

    /**
     * @param obj {@link Object}
     * @return Indicates whether another object is 'equal' to this one.*/
    @Override
    public boolean equals(Object obj) {
        if (obj == null){return false;}
        if (obj == this){return true;}
        if (!(obj instanceof Child child)){return false;}

        return this.getEmail() == child.getEmail();
    }
}
