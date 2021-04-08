package eu.ark.creditark.services.creditarkservices.repository;

import eu.ark.creditark.services.creditarkservices.model.mongo.CustomerKpis;
import eu.ark.creditark.services.creditarkservices.model.mongo.Kpi;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KPIRepository extends MongoRepository<Kpi, String> {

}
