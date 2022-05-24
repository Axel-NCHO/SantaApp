public enum OrderState {

    ONGOING("Ongoing"),
    VALIDATED("Validated"),
    PREPARED("Ready");

    private String name;
    OrderState (String state){
        this.name = state;
    }

    public String toString (){
        return this.name;
    }
}
