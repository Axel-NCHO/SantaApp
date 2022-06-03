/**
 * <h1>OrderState</h1>
 * Enumeration of possible states for an {@link Order}.
 * An order can be 'ONGOING', 'VALIDATED', or 'PREPARED'.*/

public enum OrderState {

    /* New order */
    ONGOING("Ongoing"),

    /* Order validated by Santa */
    VALIDATED("Validated"),

    /* Order ready for delivery */
    PREPARED("Ready");

    private String name;
    OrderState (String state){
        this.name = state;
    }

    public String toString (){
        return this.name;
    }
}
