import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <h1>File helper</h1>
 * File manager*/
public class FileHelper {
    /** Path to the working repository.
    *  Must be modified depending on the operating system.
    *  On Windows, it's an empty string.
    *  On Linux, it's the absolute path to the working repository.*/
    private static String appPath = "";

    public static String getAppPath(){
        return appPath;
    }

    public static void setAppPath(String newAppPath){
        appPath = newAppPath;
    }

    /**
     * Save a specified object on hard drive at the specified path by serializing it.
     * @param path the {@link String} path where the object should be saved.
     * @param obj the {@link Object} to be saved.*/
    public static void export(String path, Object obj) {
        try {
            File fichier = new File (appPath + path);
            if(!fichier.exists()){
                if(!fichier.createNewFile()){
                    System.out.println("Error while creating " + path);
                }
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(appPath + path));
            oos.writeObject(obj);
            oos.close();
        } catch(IOException e){
            System.out.println("An error has occurred");
            e.printStackTrace();
        }
    }

    /**
     * Remove a specified file from hard drive.
     * @param path the {@link String} path to the file.*/
    public static void remove(String path){
        File fichier = new File(appPath + path);
        if(fichier.exists()){
            if(!fichier.delete()){
                System.out.println("Error while suppressing " + path);
            }
        }
        else{
            System.out.println("The file " + path + " does not exist.");
        }
    }

    /**
     * Load a specified object from hard drive at the specified path by deserializing it.
     * @param path the {@link String} path to the object.
     * @return the {@link Object} loaded. The type of this object shold be converted to the real one.*/
    public static Object load(String path){
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
                System.out.println("An error has occurred.");
                e.printStackTrace();
            }
        }
        return obj;
    }

    /**
     * @param path the path to a repository.
     * @return the list of the files in the specified repository.*/
    public static String[] fileList(String path){
        return new File(appPath + path).list();
    }
}
