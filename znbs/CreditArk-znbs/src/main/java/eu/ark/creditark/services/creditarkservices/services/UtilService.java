package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;

public interface UtilService {
    public void cache() throws DatabaseConnectionException;
    public void evict();
    public String getSchema(short contextId) throws DatabaseConnectionException;
}
