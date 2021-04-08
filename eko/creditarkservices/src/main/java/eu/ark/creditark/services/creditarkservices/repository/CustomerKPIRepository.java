package eu.ark.creditark.services.creditarkservices.repository;

import eu.ark.creditark.services.creditarkservices.model.mongo.CustomerKpis;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CustomerKPIRepository extends MongoRepository<CustomerKpis, String> {
  @Query(value="{'context_id' : ?0, 'customer_id' : ?1, 'snapshot_date' : ?2}")
  //@Query(value="{'context_id' : ?0, 'customer_id' : ?1, 'snapshot_date' : ?2}", fields="{}")
  List<CustomerKpis> findOnSnapshot(String context_id, String customer_id, String snapshot_date);
}
