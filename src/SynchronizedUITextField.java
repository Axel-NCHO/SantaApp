/**
 * <h1>SynchronizedUITextField</h1>
 * A {@link UITextField} that allows read and write access management.
 * <hr>
 *
 * <h2>Why ?</h2>
 *
 * <p>
 * One {@link Thread} might need to read the content of a text field that is being used
 * by an other thread. In this case, trying to access it might cause a problem
 * if the value in the field is not complete.
 * Access management allows to wait for the field to be available
 * before trying to access it.
 * </p>
 *
 * <hr>*/

public class SynchronizedUITextField extends UITextField {

    /* Boolean that indicates whether the field is available for reading */
    private boolean availableForRead;

    /**
     * Create a new {@link SynchronizedUITextField} and set a default read access status.
     * @param availableForRead {@link Boolean}. If true, no thread can write in the field until it is passed to false.
     * If false, no thread can read the field until it is passed to true by using the setAvailableForRead() method.*/
    public SynchronizedUITextField (boolean availableForRead) {
        super();
        this.availableForRead = availableForRead;
    }

    /**
     * Write a {@link String} in a {@link SynchronizedUITextField}.
     * If the field is available for reading, the calling thread will wait till it is.
     * @param text the text to be written.*/
    public synchronized void write(String text) throws InterruptedException {
        if(availableForRead) {
            wait();
        }
        super.setText(text);
    }

    /**
     * Read a {@link String} from a {@link SynchronizedUITextField}.
     * The field must be <u><b>manually</b></u> set available for read with the 'setAvailableForRead' method
     * before using this method.
     * @return a String.*/
    public synchronized String read() throws InterruptedException {
        if(!availableForRead) {
            wait();
        }
        String text = super.getText();
        this.availableForRead = false;
        notifyAll();
        return text;
    }

    /**
     * Give read access to an external thread. This will deny write access to any thread trying to write
     * in the field untill read is done.*/
    public void setAvailableForRead() {
        this.availableForRead = true;
    }
}
