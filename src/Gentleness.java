/**
 * <h1>Gentleness</h1>
 * Enumeration of gentleness possibilities for a {@link Child}.*/

public enum Gentleness {
    BAD_BOY("Bad_Boy"),
    NICE_BOY("Nice_Boy"),
    GOOD_BOY("Good_Boy"),
    EXCELLENT_BOY("Excellent_Boy");

    private final String gentle;

    Gentleness (String gentle){
        this.gentle = gentle;
    }

    public String toString(){
        return this.gentle;
    }

}
