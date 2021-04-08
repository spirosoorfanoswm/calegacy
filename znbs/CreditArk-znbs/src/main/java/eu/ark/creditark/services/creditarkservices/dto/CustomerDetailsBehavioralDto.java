package eu.ark.creditark.services.creditarkservices.dto;

public class CustomerDetailsBehavioralDto {
    private KeyValuesDto behavioral;

    public KeyValuesDto getBehavioral() {
        if(null == behavioral) this.behavioral = new KeyValuesDto();
        return behavioral;
    }

    public void setBehavioral(KeyValuesDto behavioral) {
        this.behavioral = behavioral;
    }
}
