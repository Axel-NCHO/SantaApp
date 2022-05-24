import javax.swing.*;

public class UITextArea extends JTextArea implements UiFinals {

    public UITextArea(){
        super();
        this.setBackground(UI_TEXTFIELD_COLOR);
        this.setForeground(UI_TEXT_COLOR);
    }
}
