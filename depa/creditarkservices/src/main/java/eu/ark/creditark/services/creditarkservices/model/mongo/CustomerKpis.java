package eu.ark.creditark.services.creditarkservices.model.mongo;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;

public class CustomerKpis implements Serializable {
  @Id
  public String id;
  private String context_id;
  private String customer_id;
  private String snapshot_date;
  private List<KpiKV> kpi;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getContext_id() {
    return context_id;
  }

  public void setContext_id(String context_id) {
    this.context_id = context_id;
  }

  public String getCustomer_id() {
    return customer_id;
  }

  public void setCustomer_id(String customer_id) {
    this.customer_id = customer_id;
  }

  public String getSnapshot_date() {
    return snapshot_date;
  }

  public void setSnapshot_date(String snapshot_date) {
    this.snapshot_date = snapshot_date;
  }

  public List<KpiKV> getKpi() {
    return kpi;
  }

  public void setKpi(List<KpiKV> kpi) {
    this.kpi = kpi;
  }
}
