public class Child extends User {
    
    private Integer age;
    private String address;
    private Gentleness gentleness;
    private Boolean hasOrdered;

    public Child (String fName, String lName, Email email, String pwd, Integer age, String addr, Gentleness gentleness){
        super(fName, lName, email, pwd);
        this.age = age;
        this.address = addr;
        this.gentleness = gentleness;
        this.save();
    }

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

    

    public Integer getAge (){
        return this.age;
    }

    public String getAddress() {
        return address;
    }

    public Gentleness getGentleness (){
        return this.gentleness;
    }

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
    @Override
    public boolean equals(Object obj) {
        if (obj == null){return false;}
        if (obj == this){return true;}
        if (!(obj instanceof Child)){return false;}

        Child child = (Child)obj;
        return this.getEmail() == child.getEmail();
    }
}
