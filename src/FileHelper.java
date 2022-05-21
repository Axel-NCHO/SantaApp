import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileHelper {
    public static void export(String path, Object obj) {
        try {
            File fichier = new File (path);
            if(!fichier.exists()){
                if(!fichier.createNewFile()){
                    System.out.println("erreur lors de la création de " + path);
                }
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(obj);
            oos.close();
        } catch(IOException e){
            System.out.println("erreur détectée");
            e.printStackTrace();
        }
    }
    
    public static void remove(String path){
        File fichier = new File(path);
        if(fichier.exists()){
            if(!fichier.delete()){
                System.out.println("Erreur lors de la suppression de " + path);
            }
        }
        else{
            System.out.println("le fichier " + path + " n'existe pas");
        }
    }

    public static Object load(String path){
        Object obj = null;
        if(new File(path).exists()){
            try {
                ObjectInputStream oos = new ObjectInputStream(new FileInputStream(path));
                try {
                    obj = oos.readObject();
                    oos.close();
                } catch (ClassNotFoundException e) {
                    oos.close();
                    throw new RuntimeException(e);
                }
                oos.close();
            } catch(IOException e){
                System.out.println("erreur détectée");
                e.printStackTrace();
            }

        }
        else{
            System.out.println("le fichier " + path + " n'existe pas");
        }
        return obj;
    }
}
