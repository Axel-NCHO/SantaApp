import javax.swing.JDialog;

public class validationDialog extends JDialog {

    public validationDialog(){
        super();
    }

    public void run(){

        this.setVisible(true);
    }

    public void stop(){
        this.setVisible(false);
    }
}
