import java.awt.event.WindowEvent;

public class CloseWindowThread extends Thread {

    UIPage page;
    public CloseWindowThread(UIPage page) {
        this.page = page;
    }

    public void run() {
        page.dispatchEvent(new WindowEvent(this.page, WindowEvent.WINDOW_CLOSING));
    }
}
