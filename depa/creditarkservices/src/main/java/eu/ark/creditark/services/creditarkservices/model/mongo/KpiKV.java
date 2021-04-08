package eu.ark.creditark.services.creditarkservices.model.mongo;

import java.io.Serializable;

public class KpiKV implements Serializable {
  private String kpiId;
  private String desc;

  public String getKpiId() {
    return kpiId;
  }

  public void setKpiId(String kpiId) {
    this.kpiId = kpiId;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}
