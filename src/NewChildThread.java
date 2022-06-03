/**
 * <h1>NewChildThread</h1>
 * {@link Thread} for opening a {@link NewChildHomePage} frame.*/
public class NewChildThread extends Thread{

    /* Owner of the page to be created */
    Child child;

    public NewChildThread(Child child) {
        this.child = child;
    }

    /**
     * Create a new {@link NewChildHomePage} frame and track the selected toys
     * in background for interactive purpose.*/
    public void run(){
        NewChildHomePage newUserHomePage = new NewChildHomePage(child);
        newUserHomePage.run();

        // Thread for showing the number of selected toys
        new Thread(){public void run(){
            try {
                while (newUserHomePage.isVisible()) {
                    newUserHomePage.getNbChosenToys().write(Integer.toString(newUserHomePage.getListOfAvailableToys().getSelectedIndices().length));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }}.start();
    }
}
