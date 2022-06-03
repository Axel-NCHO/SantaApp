import javax.swing.*;

/**
 * <h1>UITextArea</h1>
 * Customized {@link JTextArea} with the constants UI_TEXTFIELD_COLOR
 * as background color and UI_TEXT_COLOR as foreground color.*/
public class UITextArea extends JTextArea implements UiFinals {

    public UITextArea(){
        super();
        this.setBackground(UI_TEXTFIELD_COLOR);
        this.setForeground(UI_TEXT_COLOR);
    }
}
