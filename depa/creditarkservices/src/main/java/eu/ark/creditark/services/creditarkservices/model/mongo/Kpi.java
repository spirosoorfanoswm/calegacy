package eu.ark.creditark.services.creditarkservices.model.mongo;

import java.io.Serializable;
import org.springframework.data.annotation.Id;

public class Kpi implements Serializable  {
  @Id
  public String id;
  private String kpiId;
  private String desc;

  public String getKpiId() {
    return kpiId;
  }

  public void setKpiId(String kpiId) {
    this.kpiId = kpiId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}
