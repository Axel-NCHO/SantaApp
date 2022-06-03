import java.awt.event.WindowEvent;

public class CloseWindowThread extends Thread {

    /**
     * <h1>CloseWindowThread</h1>
     * {@link Thread} for closing a {@link UIPage} frame.*/

    UIPage page;

    public CloseWindowThread(UIPage page) {
        this.page = page;
    }

    public void run() {
        page.dispatchEvent(new WindowEvent(this.page, WindowEvent.WINDOW_CLOSING));
    }
}
