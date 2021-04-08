package eu.ark.creditark.services.creditarkservices.dto.input;

import java.io.Serializable;
import java.util.Objects;

public class ScenarioTemplateInputDto implements Serializable {
    private String snapshotDate;
    private int contextId;
    private int comparisonPeriod;
    private String user;

    public ScenarioTemplateInputDto(String snapshotDate, int contextId, int comparisonPeriod, String user) {
        this.snapshotDate = snapshotDate;
        this.contextId = contextId;
        this.comparisonPeriod = comparisonPeriod;
        this.user = user;
    }

    public ScenarioTemplateInputDto() {
    }

    public String getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(String snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public int getContextId() {
        return contextId;
    }

    public void setContextId(int contextId) {
        this.contextId = contextId;
    }

    public int getComparisonPeriod() {
        return comparisonPeriod;
    }

    public void setComparisonPeriod(int comparisonPeriod) {
        this.comparisonPeriod = comparisonPeriod;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScenarioTemplateInputDto{");
        sb.append("snapshotDate='").append(snapshotDate).append('\'');
        sb.append(", contextId=").append(contextId);
        sb.append(", comparisonPeriod=").append(comparisonPeriod);
        sb.append(", user='").append(user).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScenarioTemplateInputDto)) return false;
        ScenarioTemplateInputDto that = (ScenarioTemplateInputDto) o;
        return getContextId() == that.getContextId() &&
                getComparisonPeriod() == that.getComparisonPeriod() &&
                Objects.equals(getSnapshotDate(), that.getSnapshotDate()) &&
                Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSnapshotDate(), getContextId(), getComparisonPeriod(), getUser());
    }
}
