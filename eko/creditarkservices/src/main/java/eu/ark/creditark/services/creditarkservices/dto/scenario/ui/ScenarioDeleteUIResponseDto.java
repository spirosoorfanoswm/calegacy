package eu.ark.creditark.services.creditarkservices.dto.scenario.ui;

import java.io.Serializable;

public class ScenarioDeleteUIResponseDto implements Serializable {
    private String resp;

    public ScenarioDeleteUIResponseDto() {
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }
}
