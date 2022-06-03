import javax.swing.*;

/**
 * <h1>UILabel</h1>
 * Customized {@link JLabel} with the constant UI_TEXT_COLOR as foreground color.*/
public class UILabel extends JLabel implements UiFinals {

    public UILabel(String text){
        super(text);
        this.setForeground(UI_TEXT_COLOR);
    }

    public UILabel(ImageIcon icon){
        super(icon);
    }
}
