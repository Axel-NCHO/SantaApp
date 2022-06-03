import java.io.Serializable;

/**
 * <h1>ReinderCare</h1>
 * Manager for medical and food resources.
 * It's used by {@link Santa} (resp. {@link MedElf}) to check
 * (resp. update) medical and food quantities.*/
public class ReinderCare implements Serializable {

    public final Integer HAY_TRESHOLD = 750;
    public final Integer MED_TRESHOLD = 40;

    /* The current hay quantity */
    private Integer hayQuantity;

    /* The current medicine quantity */
    private Integer medQuantity;

    public ReinderCare (){}
    public ReinderCare (Integer hayQt, Integer medQt){
        this.hayQuantity = hayQt;
        this.medQuantity = medQt;
    }

    /**
     * @return The current hay quantity.*/
    public Integer getHayQuantity() {
        return hayQuantity;
    }

    /**
     * @return The current medicine quantity.*/
    public Integer getMedQuantity(){
        return this.medQuantity;
    }

    public void setHayQuantity(Integer hayQuantity) {
        this.hayQuantity = hayQuantity;
    }

    public void setMedQuantity(Integer medQuantity) {
        this.medQuantity = medQuantity;
    }

}
