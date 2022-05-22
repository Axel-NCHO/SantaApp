import java.io.Serializable;

public class Toy implements Serializable {

    public String name;
    public Integer minimalAge;

    public Toy (String name, Integer minAge){

        this.name = name;
        this.minimalAge = minAge;

    }

    public String getName (){
        return this.name;
    }

    public Integer getMinimalAge(){
        return this.minimalAge;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){return false;}
        if(obj == this){return true;}
        if(!(obj instanceof Toy)){return false;}
        Toy toy = (Toy)obj;
        return toy.getName() == this.getName();

    }
    @Override
    public String toString(){
        return this.getName();
    }
}
