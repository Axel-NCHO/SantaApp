public enum Gentleness {
    //à faire, ajouter des to string
    BAD_BOY("Bad_Boy"),
    NICE_BOY("Nice_Boy"),
    GOOD_BOY("Good_Boy"),
    EXCELLENT_BOY("Excellent_Boy");

    private String gentle;

    Gentleness (String gentle){
        this.gentle = gentle;
    }

    public String toString(){
        return this.gentle;
    }

}
