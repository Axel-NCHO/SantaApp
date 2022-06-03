import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * <h1>UITextField</h1>
 * Customized {@link JTextField} with the constants UI_TEXTFIELD_COLOR
 * as background color, UI_TEXT_COLOR as foreground color and UI_BORDER_COLOR
 * as {@link LineBorder} color.*/
public class UITextField extends JTextField implements UiFinals {

    public UITextField (){
        super();
        this.setBackground(UI_TEXTFIELD_COLOR);
        this.setForeground(UI_TEXT_COLOR);
        this.setBorder(new LineBorder(UI_BORDER_COLOR, 1, true));
    }
}
