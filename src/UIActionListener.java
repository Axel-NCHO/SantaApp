import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class UIActionListener implements ActionListener {

    private UIPage page;

    public UIActionListener(UIPage page){
        this.page = page;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.page instanceof RegistrationPage){
            AccountCreationThread thread = new AccountCreationThread((RegistrationPage) this.page);
            if(thread.startThread()) {
                this.page.dispatchEvent(new WindowEvent(this.page, WindowEvent.WINDOW_CLOSING));
            }
        }

    }
}
