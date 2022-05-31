import java.io.File;

public class testToy{
    public static void main(String[] args){
        Toy test = new Toy("Mega Blocks");
        System.out.println(test.getName() + test.getMinimalAge() + test.getMaximalAge());
    }
}