import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class UIPage extends JFrame implements UiFinals, KeyListener {

    public UIPage (String title){
        BufferedImage image;
        try {
            image = ImageIO.read(new File("img/bells.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setIconImage(image);
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
