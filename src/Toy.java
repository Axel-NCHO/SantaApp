import java.io.Serializable;

public class Toy implements Serializable {

    private String name;
    private Integer minimalAge;
    private Integer maximalAge;

    public Toy (String name, Integer minAge, Integer maxAge){

        this.name = name;
        this.minimalAge = minAge;
        this.maximalAge = maxAge;
        this.save();
    }

    public String getName (){
        return this.name;
    }

    public Integer getMinimalAge(){
        return this.minimalAge;
    }
    public Integer getMaximalAge() {
        return this.maximalAge;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){return false;}
        if(obj == this){return true;}
        if(!(obj instanceof Toy)){return false;}
        Toy toy = (Toy)obj;
        return toy.getName().equalsIgnoreCase(this.getName());

    }
    @Override
    public String toString(){
        return this.getName();
    }

    private void save(){
        FileHelper.export("AppDataBase/Toys.santaDB/"+minimalAge.toString()+"-"+maximalAge.toString()+"/"+this.getName(), this);
    }
}
