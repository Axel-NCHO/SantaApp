public class SynchronizedUITextField extends UITextField {

    private boolean availableForRead;

    public SynchronizedUITextField (boolean availableForRead) {
        super();
        this.availableForRead = availableForRead;
    }

    public synchronized void write(String text) throws InterruptedException {
        if(availableForRead) {
            wait();
        }
        super.setText(text);
    }

    public synchronized String read() throws InterruptedException {
        if(!availableForRead) {
            wait();
        }
        String text = super.getText();
        this.availableForRead = false;
        notifyAll();
        return text;
    }

    public void setAvailableForRead() {
        this.availableForRead = true;
    }
}
