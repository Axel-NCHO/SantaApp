import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public abstract class UIPage extends JFrame implements UiFinals, KeyListener {

    public UIPage (String title){
        this.setTitle(APP_NAME + " - v" + APP_VERSION + " - " + title);
        this.setSize(new Dimension(1200, 700));
        this.setResizable(false);
        this.setLocationRelativeTo(null); // center the frame
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void run() {
        this.setVisible(true);
    }
}
