/**
 * <h1>EmploymentStatus</h1>
 * Enumeratiion of employment statuses for {@link Elf}.*/
public enum EmploymentStatus {
    RESIGNED("Resigned"),
    FIRED("Fired"),
    RECRUITED("Currently recruited"),
    RETIRED("Retired");

    /* The string that describes the employment status */
    private String status;

    EmploymentStatus (String status){
        this.status = status;
    }

    public String toString(){
        return this.status;
    }
}

