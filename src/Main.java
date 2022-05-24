import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String l;
        Boolean isConnected = false;
        User currentUser = null;
        while (!isConnected) {
            System.out.println("Connection :\nVotre adresse email (tapez \"nouvel utilisateur\" pour vous inscrire)");
            l = sc.nextLine();
            if (l.equals("nouvel utilisateur")) {
                /*
                String type = "";
                while(!type.equals("enfant") && !type.equals("elfMed") && !type.equals("elfPack")){
                    System.out.println("Si vous êtes un Enfant, tapez \"enfant\"\nSi vous êtes un elf Médical, tapez \"elfMed\"\nSi vous êtes un elf préparateur, tapez \"elfPack\"");
                    type = sc.nextLine();
                    if(!type.equals("enfant") && !type.equals("elfMed") && !type.equals("elfPack")){
                        System.out.println("type d'utilisateur non reconnu, veuillez recommencer");
                    }
                }*/
                Boolean correctUser = true;
                currentUser = new MedElf();
                do{
                    correctUser = true;
                    System.out.println("Entrez votre prénom");
                    currentUser.setFirstName(sc.nextLine());
                    System.out.println("Entrez votre nom");
                    currentUser.setLastName(sc.nextLine());
                    System.out.println("Entrez votre adresse mail");
                    currentUser.setEmail(sc.nextLine());
                    System.out.println("Entrez votre mot de passe");
                    String tmpMdp = sc.nextLine();
                    System.out.println("Confirmez votre mot de passe");
                    if(sc.nextLine().equals(tmpMdp)){
                        currentUser.setPassword(tmpMdp);
                    }
                    else{
                        System.out.println("Les mots de passe ne correspondent pas, veuillez recommencer");
                        correctUser = false;
                    }
                }while(!correctUser);
                currentUser.save();
                correctUser = true;
                isConnected = true;
            } else {
                String[] users = FileHelper.fileList("AppDataBase/UsersFiles.santaDB");
                int i = 0;
                while (users != null && i < users.length && !l.equals(users[i])) {
                    i++;
                }
                if (users == null || i >= users.length) {
                    System.out.println("Utilisateur inconnu, veuillez réessayer ou créer un nouveau compte");
                }
                else{
                    currentUser = (User) FileHelper.load("AppDataBase/UsersFiles.santaDB/" + users[i]);
                    String enteredPassword = "";
                    Boolean exit = false;
                    while(!enteredPassword.equals(currentUser.getPassword()) && !exit){
                        System.out.println("Entrez votre mot de passe");
                        enteredPassword = sc.nextLine();
                        if(enteredPassword.equals("exit")){
                            exit = true;
                        }
                        else if(!enteredPassword.equals(currentUser.getPassword())){
                            System.out.println("Mauvais mot de passe, réessayez ou entrez \"exit\" pour quitter");
                        }
                        else{
                            isConnected = true;
                        }
                    }
                }
            }
        }
        sc.close();
    }
}