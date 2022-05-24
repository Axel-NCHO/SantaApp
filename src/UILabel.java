import javax.swing.*;

public class UILabel extends JLabel implements UiFinals {

    public UILabel(String text){
        super(text);
        this.setForeground(UI_TEXT_COLOR);
    }

    public UILabel(ImageIcon icon){
        super(icon);
    }
}
