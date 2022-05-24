public enum EmploymentStatus {
    RESIGNED("Resigned"),
    FIRED("Fired"),
    RECRUITED("Currently recruited"),
    RETIRED("Retired");

    private String status;

    EmploymentStatus (String status){
        this.status = status;
    }

    public String toString(){
        return this.status;
    }
}

