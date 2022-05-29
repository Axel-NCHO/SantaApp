import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileHelper {
    private static String appPath = "";//"/home/paul/Documents/java/SantaAppProject/"; //modifier pour changer le chemin de l'application

    public static String getAppPath(){
        return appPath;
    }

    public static void setAppPath(String newAppPath){
        appPath = newAppPath;
    }


    public static void export(String path, Object obj) { //crée ou modifie un fichier en sérialisant l'objet passé en paramètre à l'emplacement spécifié
        try {
            File fichier = new File (appPath + path);
            if(!fichier.exists()){
                if(!fichier.createNewFile()){
                    System.out.println("erreur lors de la création de " + path);
                }
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(appPath + path));
            oos.writeObject(obj);
            oos.close();
        } catch(IOException e){
            System.out.println("erreur détectée");
            e.printStackTrace();
        }
    }
    
    public static void remove(String path){ // supprime le fichier de chemin passé en paramètre
        File fichier = new File(appPath + path);
        if(fichier.exists()){
            if(!fichier.delete()){
                System.out.println("Erreur lors de la suppression de " + path);
            }
        }
        else{
            System.out.println("le fichier " + path + " n'existe pas");
        }
    }

    public static Object load(String path){ //renvoie l'objet issu de la désérialisation du fichier de chemin passé en paramètre
        Object obj = null;
        if(new File(appPath + path).exists()){
            try {
                ObjectInputStream oos = new ObjectInputStream(new FileInputStream(appPath + path));
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

    public static String[] fileList(String path){ // renvoie la liste des fichiers dans le répertoire de chemin passé en paramètre
        return new File(appPath + path).list();
    }
}
