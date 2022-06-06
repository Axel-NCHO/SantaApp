/**
 * <h1>Santa App</h1>
 * @author Axel N'CHO & Paul REVEL - INFO 1 - ENSSAT
 * <hr>
 *<p> It's a software where children can build a whishlist of available toysnto a certain
 * amount a send it to Santa Claus. </p>
 *
 * <h2>How does it work ?</h3>
 *
 * <ul><li><b>Child-side</b></li></ul>
 * <p>A child can create an account by using name, e-mail, password (those are necessary)
 * and other personal informations that are not necessary.
 * Once the account is created, the child get to choose up to 5 toys and leave a facultative
 * message for Santa Claus. The child can now send the order see a resume of it.</p>
 * <br>
 * <p>Later, the child can reconnect and modify the content of the order only if Santa has not
 * validated it yet. If Santa cancelled it, the child is notified when he/she reconnects.</p>
 *
 * <ul><li><b>Santa-side</b></li></ul>
 * Santa is a default user.
 * <p>When he connects to his account, he has access to all the database.
 * So, he can validate or cancel ongoing orders according to whether the child has been nice
 * or not during the falling year, track all the preparation processus
 * , manage the elfs, and see statistics about the reinders. Those stats come from MedElfs
 * (Medical Elfs).</p>
 *
 * <ul><li><b>Elf-side</b></li></ul>
 * There are 2 types of Elfs : Packaging Elfs and Medical Elfs.
 *
 * <p><b>PackagingElf :</b> They prepare validated orders and notify Santa when they are ready.</p>
 * <p><b>MedElf :</b> They take care of reinders and keep the reinder care up-to-date
 * so that Santa knows what quantity of food and medicine remains.</p>
 * <hr>
 * */
public class Main {
    public static void main(String[] args){

        ConnexionPage connexionPage = new ConnexionPage();
        connexionPage.run();
    }
}
