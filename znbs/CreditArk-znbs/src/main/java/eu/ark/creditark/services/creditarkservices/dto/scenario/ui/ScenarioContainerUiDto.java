package eu.ark.creditark.services.creditarkservices.dto.scenario.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScenarioContainerUiDto extends ScenarioUI implements Serializable {
    private List<ScenarioUIDto> scenarios;

    private short contextId;
    private String snapshotDate;

    public ScenarioContainerUiDto() {
    }

    public short getContextId() {
        return contextId;
    }

    public void setContextId(short contextId) {
        this.contextId = contextId;
    }

    public String getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(String snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public List<ScenarioUIDto> getScenarios() {
        if(null == this.scenarios) this.scenarios = new ArrayList<>();
        return scenarios;
    }

    public void setScenarios(List<ScenarioUIDto> scenarios) {
        this.scenarios = scenarios;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScenarioContainerUiDto{");
        sb.append("scenarios=").append(scenarios);
        sb.append(", contextId=").append(contextId);
        sb.append(", snapshotDate='").append(snapshotDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
