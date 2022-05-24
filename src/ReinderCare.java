import java.io.Serializable;

public class ReinderCare implements Serializable {

    public final Integer HAY_TRESHOLD = 750;
    public final Integer MED_TRESHOLD = 40;

    private Integer hayQuantity;
    private Integer medQuantity;

    public ReinderCare (){}
    public ReinderCare (Integer hayQt, Integer medQt){
        this.hayQuantity = hayQt;
        this.medQuantity = medQt;
    }

    public Integer getHayQuantity() {
        return hayQuantity;
    }

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
