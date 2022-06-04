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
        if (page instanceof SantaHomePage) {
            SantaHomePage santaPage = (SantaHomePage) page;
            for (Component panel : santaPage.getContentPanel().getComponents()) {
                if (panel.isVisible()) {
                    childEmail = panel.getName();
                    break;
                }
            }
        }

        if (page instanceof PackagingElfHomePage) {
            PackagingElfHomePage packagingElfPage = (PackagingElfHomePage) page;
            for (Component panel : packagingElfPage.getContentPanel().getComponents()) {
                if (panel.isVisible()) {
                    childEmail = panel.getName();
                    break;
                }
            }
        }

    }
    public String startThread() {
        run();
        return childEmail;
    }
}
