import java.io.File;
import java.io.Serial;
import java.io.Serializable;

/**
 * <h1>Toy</h1>
 * Represents a toy.
 * A toy has a name and an age range.*/
public class Toy implements Serializable {
    @Serial
    private static final long serialVersionUID = 794993212;

    /* Name of a toy */
    private String name;

    /* The minimal age for a child to have this toy */
    private Integer minimalAge;

    /* The maximal age for a child to have this toy */
    private Integer maximalAge;

    /**
     * Create a new {@link Toy} and save it in the database.*/
    public Toy (String name, Integer minAge, Integer maxAge){

        this.name = name;
        this.minimalAge = minAge;
        this.maximalAge = maxAge;
        this.save();
    }

    /**
     * Load an existing {@link Toy} from database.*/
    public Toy (String name){//memory loading version
        this.name = name;
        File toyDir = new File(FileHelper.getAppPath() + "AppDataBase/Toys.santaDB");
        Toy loadedToy = null;
        int i = 0;
        while(i < toyDir.list().length && loadedToy == null){
            System.out.println("AppDataBase/Toys.santaDB/" + toyDir.list()[i]);
            loadedToy = (Toy) FileHelper.load("AppDataBase/Toys.santaDB/" + toyDir.list()[i] + "/" + name);
            i++;
        }
        if(loadedToy == null){
            System.out.println("Error while loading the toy " + name);
        }
        else{
            this.minimalAge = loadedToy.minimalAge;
            this.maximalAge = loadedToy.maximalAge;
        }
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

    /**
     * Save a {@link Toy} in the database.*/
    private void save(){
        FileHelper.export("AppDataBase/Toys.santaDB/"+minimalAge.toString()+"-"+maximalAge.toString()+"/"+this.getName(), this);
    }
}
