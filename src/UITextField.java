import javax.swing.*;
import javax.swing.border.LineBorder;

public class UITextField extends JTextField implements UiFinals {

    public UITextField (){
        super();
        this.setBackground(UI_TEXTFIELD_COLOR);
        this.setForeground(UI_TEXT_COLOR);
        this.setBorder(new LineBorder(UI_BORDER_COLOR, 1, true));
    }
}
