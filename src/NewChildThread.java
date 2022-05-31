public class NewChildThread extends Thread{

    Child child;
    public NewChildThread(Child child) {
        this.child = child;
    }

    public void run(){
        NewChildHomePage newUserHomePage = new NewChildHomePage(child);
        newUserHomePage.run();

        // Thread for showing the number of selected toys
        new Thread(){public void run(){
            try {
                while (newUserHomePage.isVisible()) {
                    newUserHomePage.getNbChoosenToys().write(Integer.toString(newUserHomePage.getListOfAvailableToys().getSelectedIndices().length));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }}.start();
    }
}
