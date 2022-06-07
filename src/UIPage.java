import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <h1>UIPage</h1>
 * The <u>abstract class</u> UIPage defines the commons elements to all the frames
 * of the user interface. It must be extended to create new types of frame.*/
public abstract class UIPage extends JFrame implements UiFinals, KeyListener {

    public UIPage (String title){
        BufferedImage image;
        try {
            image = ImageIO.read(new File(FileHelper.getAppPath() + "img/bells.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setIconImage(image);
        this.setTitle(APP_NAME + " - v" + APP_VERSION + " - " + title);
        this.setSize(new Dimension(1200, 700));
        this.setResizable(false);
        this.setLocationRelativeTo(null); // center the frame
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // closing a frame does not end the whole program
    }

    /**
     * Show the UIPage object*/
    public void run() {
        this.setVisible(true);
    }
}
