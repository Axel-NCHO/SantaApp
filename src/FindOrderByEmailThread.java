import java.awt.Component;

/**
 * <h1>FindOrderByEmailThread</h1>
 * {@link Thread} for finding an order by using an e-mail.
 * The {@link javax.swing.JPanel} panels that show the content of the orders have an attribute 'name'
 * that is equal to the name of the child whose order is shown.*/
public class FindOrderByEmailThread extends Thread {
    String childEmail = "";
    UIPage page;

    public FindOrderByEmailThread (UIPage page) {
        this.page = page;
    }

    public void run() {
        SantaHomePage santaPage = null;
        if (page instanceof SantaHomePage) {
             santaPage = (SantaHomePage) page;
        }
        if (santaPage != null) {
            for (Component panel : santaPage.getContentPanel().getComponents()) {
                if (panel.isVisible()) {
                    childEmail = panel.getName();
                }
            }
        }
    }
    public String startThread() {
        start();
        return childEmail;
    }
}
