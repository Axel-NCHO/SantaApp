/**
 * <h1>NewChildThread</h1>
 * {@link Thread} for opening a {@link NewChildHomePage} frame.*/
public class NewChildThread extends Thread{

    /* Owner of the page to be created */
    private Child child;
    private NewChildHomePage page;

    public NewChildThread(Child child) {
        this.child = child;
        page = new NewChildHomePage(child);
    }

    /**
     * Create a new {@link NewChildHomePage} frame and track the selected toys
     * in background for interactive purpose.*/
    public void run(){
        //NewChildHomePage newUserHomePage = new NewChildHomePage(child);
        page.run();

        new WelcomeThread(page).start();

        // Thread for showing the number of selected toys
        new Thread(){public void run(){
            try {
                while (page.isVisible()) {
                    page.getNbChosenToys().write(Integer.toString(page.getListOfAvailableToys().getSelectedIndices().length));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }}.start();
    }

    public NewChildHomePage getPage() {
        return page;
    }
}
